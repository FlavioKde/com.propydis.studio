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




    @GetMapping("/getAll")
    public ResponseEntity<List<ProjectDTO>> getAll() {

        /*
        List<ProjectDTO> projects = projectService.findAll()
                .stream()
                .map(ProjectMapper::toDTO)
                .collect(Collectors.toList());

         */


        List<ProjectDTO> projects = projectService.findAll().stream()
                .map(project -> projectService.getProjectDTOById(project.getId()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(projects);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProjectDTO> getById(@PathVariable String id) {

        /*
        Project project = projectService.findById(id);

        return ResponseEntity.ok(ProjectMapper.toDTO(project));


         */
        ProjectDTO project = projectService.getProjectDTOById(id);
        return ResponseEntity.ok(project);


    }


}
