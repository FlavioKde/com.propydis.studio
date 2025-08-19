package com.propydis.studio.dto.mongodb.mapper;

import com.propydis.studio.dto.mongodb.ProjectCreateDTO;
import com.propydis.studio.dto.mongodb.ProjectDTO;
import com.propydis.studio.model.mongodb.Project;

import java.util.stream.Collectors;

public class ProjectMapper {

    public static Project toEntity(ProjectCreateDTO projectCreateDTO) {
        Project project = new Project();
        project.setName(projectCreateDTO.getName());
        project.setDescription(projectCreateDTO.getDescription());
        project.setPhotos(
                projectCreateDTO.getPhotoDTO()
                        .stream()
                        .map(PhotoMapper::toEntity)
                        .collect(Collectors.toList())
        );

        return project;
    }

    public static ProjectDTO toDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        projectDTO.setDescription(project.getDescription());
        projectDTO.setPhotoDTO(
                project.getPhotos().stream()
                        .map(PhotoMapper::toDTO)
                                .collect(Collectors.toList()));

        projectDTO.setCreatedAt(project.getCreatedAt());
        projectDTO.setUpdatedAt(project.getUpdatedAt());
        projectDTO.setProjectStatus(project.getProjectStatus());

        return projectDTO;
    }
}
