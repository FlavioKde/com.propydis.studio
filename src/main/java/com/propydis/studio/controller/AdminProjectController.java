package com.propydis.studio.controller;


import com.propydis.studio.config.ApiConfig;
import com.propydis.studio.dto.mongodb.PhotoDTO;
import com.propydis.studio.dto.mongodb.ProjectCreateDTO;
import com.propydis.studio.dto.mongodb.ProjectDTO;
import com.propydis.studio.dto.mongodb.mapper.ProjectMapper;
import com.propydis.studio.infrastucture.cloudinary.CloudinaryService;
import com.propydis.studio.infrastucture.validation.ImageValidator;
import com.propydis.studio.model.mongodb.Project;
import com.propydis.studio.service.mongodb.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiConfig.API_BASE_PATH + "/admin/project")
public class AdminProjectController {

    private final ProjectService projectService;
    private final CloudinaryService cloudinaryService;


    public AdminProjectController(ProjectService projectService, CloudinaryService cloudinaryService) {
        this.projectService = projectService;
        this.cloudinaryService = cloudinaryService;
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProjectDTO>create(@RequestParam("name") String name,
                                            @RequestParam("description") String description,
                                            @RequestParam(value = "photos", required = false) MultipartFile [] photos)
    {
        List<PhotoDTO>photoDTOS = new ArrayList<>();

        if (photos != null){
            for (MultipartFile photo : photos){
                try{
                    ImageValidator.validate(photo);

                    String url = cloudinaryService.uploadImage(photo);
                    PhotoDTO photoDTO = new PhotoDTO();
                    photoDTO.setUrl(url);
                    photoDTO.setAltText(photo.getOriginalFilename());
                    photoDTOS.add(photoDTO);
                }catch (IOException e){
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .build();
                }
            }
        }

        ProjectCreateDTO projectCreateDTO = new ProjectCreateDTO();

        projectCreateDTO.setName(name);
        projectCreateDTO.setDescription(description);
        projectCreateDTO.setPhotosDTO(photoDTOS);

        Project project = ProjectMapper.toEntity(projectCreateDTO);
        Project savedProject = projectService.save(project);

        return ResponseEntity.ok(ProjectMapper.toDTO(savedProject));

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable String id,
                                                    @RequestParam("name") String name,
                                                    @RequestParam("description") String description,
                                                    @RequestParam(value = "photos", required = false) MultipartFile[] photos,
                                                    @RequestParam(value = "deletePhotoIds", required = false) List<String> deletePhotoIds
    ) {
        Project existingProject = projectService.findById(id);
        if (existingProject == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


        List<PhotoDTO> photoDTOS = ProjectMapper.toDTO(existingProject).getPhotosDTO();
        if (photoDTOS == null) photoDTOS = new ArrayList<>();


        if (deletePhotoIds != null && !deletePhotoIds.isEmpty()) {
            photoDTOS.removeIf(photo -> deletePhotoIds.contains(photo.getId()));
        }


        if (photos != null) {
            for (MultipartFile photo : photos) {

                try {
                    ImageValidator.validate(photo);

                    String url = cloudinaryService.uploadImage(photo);
                    PhotoDTO photoDTO = new PhotoDTO();
                    photoDTO.setUrl(url);
                    photoDTO.setAltText(photo.getOriginalFilename());
                    photoDTOS.add(photoDTO);
                } catch (IOException e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            }
        }


        ProjectCreateDTO dto = new ProjectCreateDTO();
        dto.setName(name);
        dto.setDescription(description);
        dto.setPhotosDTO(photoDTOS);


        Project updatedProject = projectService.update(ProjectMapper.toEntity(dto, id), id);
        return ResponseEntity.ok(ProjectMapper.toDTO(updatedProject));
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ProjectDTO>> getAll() {
        List<ProjectDTO> projects = projectService.findAll()
                .stream()
                .map(ProjectMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(projects);

    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProjectDTO> getById(@PathVariable String id) {
        Project project = projectService.findById(id);

        return ResponseEntity.ok(ProjectMapper.toDTO(project));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        projectService.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
