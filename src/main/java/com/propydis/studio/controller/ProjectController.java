package com.propydis.studio.controller;


import com.propydis.studio.config.ApiConfig;
import com.propydis.studio.dto.project.project.ProjectDTO;
import com.propydis.studio.application.project.project.ProjectService;
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




    @GetMapping("/getAll")
    public ResponseEntity<List<ProjectDTO>> getAll() {

        List<ProjectDTO> projects = projectService.findAll().stream()
                .map(project -> projectService.getProjectDTOById(project.getId()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(projects);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProjectDTO> getById(@PathVariable String id) {

        ProjectDTO project = projectService.getProjectDTOById(id);
        return ResponseEntity.ok(project);


    }


}
