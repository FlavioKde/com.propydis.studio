package com.propydis.studio.controller;


import com.propydis.studio.config.ApiConfig;
import com.propydis.studio.dto.project.project.ProjectCreateDTO;
import com.propydis.studio.dto.project.project.ProjectDTO;
import com.propydis.studio.shared.mapper.ProjectMapper;
import com.propydis.studio.infrastructure.cloudinary.CloudinaryService;
import com.propydis.studio.infrastructure.validation.ImageValidator;
import com.propydis.studio.domain.project.Photo;
import com.propydis.studio.domain.project.project.Project;
import com.propydis.studio.application.project.project.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<ProjectDTO> create(@RequestParam("name") String name,
                                             @RequestParam("description") String description,
                                             @RequestParam(value = "photos", required = false) MultipartFile[] photos) {
        List<Photo> photoEntities = new ArrayList<>();

        if (photos != null) {
            for (MultipartFile photo : photos) {
                try {
                    ImageValidator.validate(photo);
                    Map uploadResult = cloudinaryService.uploadImage(photo);

                    String url = uploadResult.get("secure_url").toString();
                    String publicId = uploadResult.get("public_id").toString();

                    Photo photoEntity = new Photo();
                    photoEntity.setUrl(url);
                    photoEntity.setPublicId(publicId);
                    photoEntity.setAltText(photo.getOriginalFilename());

                    photoEntities.add(photoEntity);
                } catch (IOException e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            }
        }

        ProjectCreateDTO dto = new ProjectCreateDTO();
        dto.setName(name);
        dto.setDescription(description);

        Project project = ProjectMapper.toEntity(dto, photoEntities);


        Project savedProject = projectService.save(project, photoEntities);

        ProjectDTO responseDTO = projectService.getProjectDTOById(savedProject.getId());



        return ResponseEntity.ok(responseDTO);
    }





    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable String id,
                                                    @RequestParam("name") String name,
                                                    @RequestParam("description") String description,
                                                    @RequestParam(value = "photos", required = false) MultipartFile[] photos,
                                                    @RequestParam(value = "deletePhotoIds", required = false) List<String> deletePhotoIds) {

        List<Photo> newPhotoEntities = new ArrayList<>();

        if (photos != null) {
            for (MultipartFile photo : photos) {
                try {
                    ImageValidator.validate(photo);
                    Map uploadResult = cloudinaryService.uploadImage(photo);

                    String url = uploadResult.get("secure_url").toString();
                    String publicId = uploadResult.get("public_id").toString();

                    Photo photoEntity = new Photo();
                    photoEntity.setUrl(url);
                    photoEntity.setPublicId(publicId);
                    photoEntity.setAltText(photo.getOriginalFilename());

                    newPhotoEntities.add(photoEntity);
                } catch (IOException e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            }
        }

        ProjectCreateDTO dto = new ProjectCreateDTO();
        dto.setName(name);
        dto.setDescription(description);

        Project project = ProjectMapper.toEntity(dto, newPhotoEntities, id);
        Project updatedProject = projectService.update(project, newPhotoEntities, deletePhotoIds, id);

        ProjectDTO responseDTO = projectService.getProjectDTOById(updatedProject.getId());

        System.out.println("📤 DTO con fotos: " + responseDTO.getPhotos().stream().map(p -> p.getUrl()).collect(Collectors.toList()));
        return ResponseEntity.ok(responseDTO);
    }



    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ProjectDTO>> getAll() {
        List<ProjectDTO> projects = projectService.findAll().stream()
                .map(project -> projectService.getProjectDTOById(project.getId()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(projects);
    }



    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProjectDTO> getById(@PathVariable String id) {
        Project project = projectService.findById(id);

        ProjectDTO dto = projectService.getProjectDTOById(id);
        return ResponseEntity.ok(dto);
    }




    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        projectService.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
