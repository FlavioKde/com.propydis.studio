package com.propydis.studio.controller;


import com.propydis.studio.config.ApiConfig;
import com.propydis.studio.dto.mongodb.ProjectCreateDTO;
import com.propydis.studio.dto.mongodb.ProjectDTO;
import com.propydis.studio.dto.mongodb.mapper.ProjectMapper;
import com.propydis.studio.model.mongodb.Project;
import com.propydis.studio.service.mongodb.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiConfig.API_BASE_PATH + "/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/save")
    public ResponseEntity<ProjectDTO> create(@Valid @RequestBody ProjectCreateDTO projectCreateDTO) {
        Project project = ProjectMapper.toEntity(projectCreateDTO);
        Project savedProject = projectService.save(project);

        return ResponseEntity.ok(ProjectMapper.toDTO(savedProject));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDTO> updatedProject(@PathVariable String id, @Valid @RequestBody ProjectCreateDTO projectCreateDTO) {
        Project project = ProjectMapper.toEntity(projectCreateDTO);
        Project updated = projectService.update(project, id);

        return ResponseEntity.ok(ProjectMapper.toDTO(updated));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProjectDTO>> getAll() {
        List<ProjectDTO> projects = projectService.findAll()
                .stream()
                .map(ProjectMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(projects);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProjectDTO> getById(@PathVariable String id) {
        Project project = projectService.findById(id);

        return ResponseEntity.ok(ProjectMapper.toDTO(project));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        projectService.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
