package com.propydis.studio.dto.mongodb.mapper;

import com.propydis.studio.dto.mongodb.ProjectCreateDTO;
import com.propydis.studio.dto.mongodb.ProjectDTO;
import com.propydis.studio.domain.project.Photo;
import com.propydis.studio.domain.project.Project;
import com.propydis.studio.shared.mapper.PhotoDtoMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectMapper {

    public static Project toEntity(ProjectCreateDTO projectCreateDTO, List<Photo> photos) {
        Project project = new Project();

        project.setName(projectCreateDTO.getName());
        project.setDescription(projectCreateDTO.getDescription());


        List<String> photoIds = photos.stream()
                .map(Photo::getId)
                .collect(Collectors.toList());

        project.setPhotoIds(photoIds);



        return project;
    }



        public static ProjectDTO toDTO(Project project, List<Photo> photos) {
            ProjectDTO dto = new ProjectDTO();
            dto.setId(project.getId());
            dto.setName(project.getName());
            dto.setDescription(project.getDescription());
            dto.setCreatedAt(project.getCreatedAt());
            dto.setUpdatedAt(project.getUpdatedAt());
            dto.setProjectStatus(project.getProjectStatus());
            dto.setPhotos(PhotoDtoMapper.toDtoList(photos));

            return dto;

    }

    public static Project toEntity(ProjectCreateDTO dto, List<Photo> photos, String id) {
        Project project = toEntity(dto, photos);
        project.setId(id);
        return project;
    }


}
