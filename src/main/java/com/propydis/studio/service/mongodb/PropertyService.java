/*

package com.propydis.studio.service.mongodb;



import com.propydis.studio.exception.exceptions.NotFoundByIdException;
import com.propydis.studio.model.mongodb.Property;
import com.propydis.studio.repository.mongodb.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    private PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public Property save(Property property) {

        validatedPrice(property);
        return propertyRepository.save(property);

    }

    public Property update(Property property, String id) {
        Property existing = propertyRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException(id, "property"));

        existing.setName(property.getName());
        existing.setDescription(property.getDescription());
        existing.setPhotos(property.getPhotos());
        existing.setPropertyStatus(property.getPropertyStatus());
        existing.setPriceValue(property.getPriceValue());
        existing.setPriceText(property.getPriceText());
        validatedPrice(existing);

        return propertyRepository.save(existing);
    }

    public List<Property> findAll() {
        return propertyRepository.findAll();
    }

    public Property findById(String id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException(id, "property"));
    }

    public void deleteById(String id) {
        Property existing = propertyRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException(id, "property"));

        propertyRepository.delete(existing);
    }

    public void validatedPrice(Property property) {
        if (property.getPriceText() == null && property.getPriceValue() == null) {
            throw new IllegalArgumentException("Debe especificarse un precio o una descripción.");
        }
    }
}


 */



package com.propydis.studio.service.mongodb;

import com.propydis.studio.dto.mongodb.PropertyDTO;
import com.propydis.studio.dto.mongodb.mapper.PropertyMapper;
import com.propydis.studio.exception.exceptions.NotFoundByIdException;
import com.propydis.studio.infrastucture.cloudinary.CloudinaryService;
import com.propydis.studio.model.mongodb.Photo;
import com.propydis.studio.model.mongodb.Property;
import com.propydis.studio.repository.mongodb.PhotoRepository;
import com.propydis.studio.repository.mongodb.PropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
            existing.getPhotoIds().addAll(newPhotoIds);
        }

        return propertyRepository.save(existing);
    }

    public List<Property> findAll() {
        return propertyRepository.findAll();
    }

    public Property findById(String id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException(id, "property"));
    }

    public void deleteById(String id) {
        Property existing = findById(id);

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
        propertyRepository.delete(existing);
    }

    public PropertyDTO getPropertyDTOById(String id) {
        Property property = findById(id);
        List<Photo> photos = photoRepository.findAllById(property.getPhotoIds());
        return PropertyMapper.toDTO(property, photos);
    }
}