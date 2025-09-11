package com.propydis.studio.service.mongodb;

import java.util.List;

import com.propydis.studio.dto.mongodb.ProjectDTO;
import com.propydis.studio.dto.mongodb.mapper.ProjectMapper;
import com.propydis.studio.shared.exception.exceptions.NotFoundByIdException;
import com.propydis.studio.infrastucture.cloudinary.CloudinaryService;
import com.propydis.studio.model.mongodb.Photo;
import com.propydis.studio.model.mongodb.Project;
import com.propydis.studio.repository.mongodb.PhotoRepository;
import com.propydis.studio.repository.mongodb.ProjectRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    /*
    private ProjectRepository projectRepository;
    private PhotoRepository photoRepository;
    private CloudinaryService cloudinaryService;

    public ProjectService(ProjectRepository projectRepository, PhotoRepository photoRepository, CloudinaryService cloudinaryService) {

        this.projectRepository = projectRepository;
        this.photoRepository = photoRepository;
        this.cloudinaryService = cloudinaryService;

    }

    public Project save(Project project, List<MultipartFile> files) {
        List<Photo> photos = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                Map uploadResult = cloudinaryService.uploadImage(file);

                String url = uploadResult.get("secure_url").toString();
                String publicId = uploadResult.get("public_id").toString();

                Photo photo = new Photo();
                photo.setUrl(url);
                photo.setPublicId(publicId);
                photo.setAltText("Foto del proyecto"); // o lo que venga del frontend

                photos.add(photo);
            } catch (IOException e) {
                throw new RuntimeException("Error al subir imagen a Cloudinary", e);
            }
        }

        List<Photo> savedPhotos = photoRepository.saveAll(photos);

        List<String> photoIds = savedPhotos.stream()
                .map(Photo::getId)
                .collect(Collectors.toList());

        project.setPhotoIds(photoIds);

        return projectRepository.save(project);
    }

    public Project update(Project project,
                          List<MultipartFile> newFiles,
                          List<String> deletePhotoIds,
                          String id) {

        Project existing = projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException(id, "project"));

        // Actualizar campos básicos
        existing.setName(project.getName());
        existing.setDescription(project.getDescription());

        // Eliminar fotos individuales
        if (deletePhotoIds != null && !deletePhotoIds.isEmpty()) {
            for (String photoId : deletePhotoIds) {
                Photo photo = photoRepository.findById(photoId).orElse(null);
                if (photo != null && photo.getPublicId() != null) {
                    try {
                        cloudinaryService.deleteImage(photo.getPublicId());
                    } catch (IOException e) {
                        System.err.println("Error al eliminar imagen de Cloudinary: " + photo.getPublicId());
                    }
                }
            }
            photoRepository.deleteAllById(deletePhotoIds);
            existing.getPhotoIds().removeAll(deletePhotoIds);
        }

        // Agregar nuevas fotos
        if (newFiles != null && !newFiles.isEmpty()) {
            List<Photo> photos = new ArrayList<>();

            for (MultipartFile file : newFiles) {
                try {
                    Map uploadResult = cloudinaryService.uploadImage(file);
                    String url = uploadResult.get("secure_url").toString();
                    String publicId = uploadResult.get("public_id").toString();

                    Photo photo = new Photo();
                    photo.setUrl(url);
                    photo.setPublicId(publicId);
                    photo.setAltText("Foto actualizada");

                    photos.add(photo);
                } catch (IOException e) {
                    throw new RuntimeException("Error al subir imagen a Cloudinary", e);
                }
            }

            List<Photo> savedPhotos = photoRepository.saveAll(photos);
            List<String> newPhotoIds = savedPhotos.stream()
                    .map(Photo::getId)
                    .collect(Collectors.toList());

            existing.getPhotoIds().addAll(newPhotoIds);
        }

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

        // Eliminar imágenes de Cloudinary
        for (String photoId : existing.getPhotoIds()) {
            Photo photo = photoRepository.findById(photoId).orElse(null);
            if (photo != null && photo.getPublicId() != null) {
                try {
                    cloudinaryService.deleteImage(photo.getPublicId());
                } catch (IOException e) {
                    // Podés loguear el error o lanzar una excepción custom
                    System.err.println("Error al eliminar imagen de Cloudinary: " + photo.getPublicId());
                }
            }
        }

        // Eliminar fotos de MongoDB
        photoRepository.deleteAllById(existing.getPhotoIds());

        // Eliminar el proyecto
        projectRepository.delete(existing);
    }

    public ProjectDTO getProjectDTOById(String id) {
        Project project = findById(id);
        List<Photo> photos = photoRepository.findAllById(project.getPhotoIds());
        return ProjectMapper.toDTO(project, photos);
    }

     */



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

        public Project update(Project project,
                              List<Photo> newPhotos,
                              List<String> deletePhotoIds,
                              String id) {

            Project existing = projectRepository.findById(id)
                    .orElseThrow(() -> new NotFoundByIdException(id, "project"));

            // Actualizar campos básicos
            existing.setName(project.getName());
            existing.setDescription(project.getDescription());

            // Eliminar fotos individuales
            if (deletePhotoIds != null && !deletePhotoIds.isEmpty()) {
                for (String photoId : deletePhotoIds) {
                    Photo photo = photoRepository.findById(photoId).orElse(null);
                    if (photo != null && photo.getPublicId() != null) {
                        try {
                            cloudinaryService.deleteImage(photo.getPublicId());
                        } catch (IOException e) {
                            System.err.println("Error al eliminar imagen de Cloudinary: " + photo.getPublicId());
                        }
                    }
                }
                photoRepository.deleteAllById(deletePhotoIds);
                existing.getPhotoIds().removeAll(deletePhotoIds);
            }

            // Agregar nuevas fotos
            if (newPhotos != null && !newPhotos.isEmpty()) {
                List<Photo> savedPhotos = photoRepository.saveAll(newPhotos);
                List<String> newPhotoIds = savedPhotos.stream()
                        .map(Photo::getId)
                        .collect(Collectors.toList());

                existing.getPhotoIds().addAll(newPhotoIds);
            }

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
            projectRepository.delete(existing);
        }

        public ProjectDTO getProjectDTOById(String id) {
            Project project = findById(id);
            List<Photo> photos = photoRepository.findAllById(project.getPhotoIds());
            return ProjectMapper.toDTO(project, photos);
        }





}