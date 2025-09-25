package com.propydis.studio.application.project.project;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.propydis.studio.dto.project.project.ProjectDTO;
import com.propydis.studio.shared.mapper.ProjectMapper;
import com.propydis.studio.shared.exception.exceptions.NotFoundByIdException;
import com.propydis.studio.infrastructure.cloudinary.CloudinaryService;
import com.propydis.studio.domain.project.photo.Photo;
import com.propydis.studio.domain.project.project.Project;
import com.propydis.studio.domain.project.photo.repository.PhotoRepository;
import com.propydis.studio.domain.project.project.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final PhotoRepository photoRepository;
    private final CloudinaryService cloudinaryService;

    public ProjectService(ProjectRepository projectRepository,
                          PhotoRepository photoRepository,
                          CloudinaryService cloudinaryService) {
        this.projectRepository = projectRepository;
        this.photoRepository = photoRepository;
        this.cloudinaryService = cloudinaryService;
    }

    public Project save(Project project, List<Photo> photos) {
        List<Photo> savedPhotos = photoRepository.saveAll(photos);

        List<String> photoIds = savedPhotos.stream()
                .map(Photo::getId)
                .collect(Collectors.toList());

        project.setPhotoIds(photoIds);

        return projectRepository.save(project);
    }

    public Project update(Project project, List<Photo> newPhotos, List<String> deletePhotoIds, String id) {
        Project existing = projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException(id, "project"));

        System.out.println("Actualizando proyecto con ID: " + id);

        if (deletePhotoIds != null && !deletePhotoIds.isEmpty()) {
            for (String photoId : deletePhotoIds) {
                Photo photo = photoRepository.findById(photoId).orElse(null);
                if (photo != null && photo.getPublicId() != null) {
                    try {
                        cloudinaryService.deleteImage(photo.getPublicId());
                    } catch (IOException e) {
                        System.out.println("⚠️ Error al eliminar de Cloudinary: " + photo.getPublicId());
                    }
                }
            }
            photoRepository.deleteAllById(deletePhotoIds);
            existing.getPhotoIds().removeAll(deletePhotoIds);
        }

        if (newPhotos != null && !newPhotos.isEmpty()) {
            List<Photo> savedPhotos = photoRepository.saveAll(newPhotos);
            List<String> newPhotoIds = savedPhotos.stream()
                    .map(Photo::getId)
                    .collect(Collectors.toList());

            if (existing.getPhotoIds() == null) {
                existing.setPhotoIds(new ArrayList<>());
            }
            existing.getPhotoIds().addAll(newPhotoIds);
        }

        existing.setName(project.getName());
        existing.setDescription(project.getDescription());
        existing.setUpdatedAt(LocalDateTime.now());
        existing.setProjectStatus(project.getProjectStatus());

        return projectRepository.save(existing);
    }





    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project findById(String id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException(id, "project"));
    }

    public void deleteById(String id) {
        Project existing = projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException(id, "project"));

        for (String photoId : existing.getPhotoIds()) {
            Photo photo = photoRepository.findById(photoId).orElse(null);
            if (photo != null && photo.getPublicId() != null) {
                try {
                    cloudinaryService.deleteImage(photo.getPublicId());
                } catch (IOException e) {
                    System.err.println("Error al eliminar imagen de Cloudinary: " + photo.getPublicId());
                }
            }
        }

        photoRepository.deleteAllById(existing.getPhotoIds());
        projectRepository.deleteById(id);
    }

    public ProjectDTO getProjectDTOById(String id) {
        Project project = findById(id);

        List<Photo> photos = photoRepository.findAllById(project.getPhotoIds());

        return ProjectMapper.toDTO(project, photos);
    }



}





