package com.propydis.studio.application.project.property;

import com.propydis.studio.dto.project.property.PropertyDTO;
import com.propydis.studio.shared.mapper.PropertyMapper;
import com.propydis.studio.shared.exception.exceptions.NotFoundByIdException;
import com.propydis.studio.infrastructure.cloudinary.CloudinaryService;
import com.propydis.studio.domain.project.photo.Photo;
import com.propydis.studio.domain.project.property.Property;
import com.propydis.studio.domain.project.photo.repository.PhotoRepository;
import com.propydis.studio.domain.project.property.repository.PropertyRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final PhotoRepository photoRepository;
    private final CloudinaryService cloudinaryService;

    public PropertyService(PropertyRepository propertyRepository,
                           PhotoRepository photoRepository,
                           CloudinaryService cloudinaryService) {
        this.propertyRepository = propertyRepository;
        this.photoRepository = photoRepository;
        this.cloudinaryService = cloudinaryService;
    }

    public Property save(Property property, List<Photo> photos) {
        List<Photo> savedPhotos = photoRepository.saveAll(photos);
        List<String> photoIds = savedPhotos.stream()
                .map(Photo::getId)
                .collect(Collectors.toList());
        property.setPhotoIds(photoIds);

        return propertyRepository.save(property);
    }


    public Property update(Property property,
                           List<Photo> newPhotos,
                           List<String> deletePhotoIds,
                           String id) {

        Property existing = propertyRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException(id, "property"));

        existing.setName(property.getName());
        existing.setDescription(property.getDescription());

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

        existing.setUpdatedAt(LocalDateTime.now());
        existing.setPropertyStatus(property.getPropertyStatus());
        existing.setPriceText(property.getPriceText());
        existing.setPriceValue(property.getPriceValue());


        return propertyRepository.save(existing);
    }


    public List<Property> findAll() {
        return propertyRepository.findAll();
    }

    @Cacheable(value = "property", key = "#id")
    public Property findById(String id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException(id, "property"));
    }


    public void deleteById(String id) {
        Property existing = propertyRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException(id, "property"));

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
        propertyRepository.deleteById(id);
    }


    public PropertyDTO getPropertyDTOById(String id) {
        Property property = findById(id);

        List<Photo> photos = photoRepository.findAllById(property.getPhotoIds());

        return PropertyMapper.toDTO(property, photos);
    }
}