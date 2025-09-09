/*
package com.propydis.studio.dto.mongodb.mapper;



import com.propydis.studio.dto.mongodb.PropertyCreateDTO;
import com.propydis.studio.dto.mongodb.PropertyDTO;
import com.propydis.studio.model.mongodb.Property;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PropertyMapper {

    public static Property toEntity(PropertyCreateDTO dto) {
        Property property = new Property();
        property.setName(dto.getName());
        property.setDescription(dto.getDescription());
        property.setPhotos(
                Optional.ofNullable(dto.getPhotos())
                        .orElse(List.of())
                        .stream()
                        .map(PhotoMapper::toEntity)
                        .collect(Collectors.toList())
        );
        property.setPriceValue(dto.getPriceValue());
        property.setPriceText(dto.getPriceText());

        return property;
    }

    public static PropertyDTO toDTO(Property entity) {
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setId(entity.getId());
        propertyDTO.setName(entity.getName());
        propertyDTO.setDescription(entity.getDescription());
        propertyDTO.setPhotos(
                Optional.ofNullable(entity.getPhotos())
                        .orElse(List.of())
                        .stream()
                        .map(PhotoMapper::toDTO)
                        .collect(Collectors.toList())
        );
        propertyDTO.setCreatedAt(entity.getCreatedAt());
        propertyDTO.setUpdatedAt(entity.getUpdatedAt());
        propertyDTO.setPropertyStatus(entity.getPropertyStatus());
        propertyDTO.setPriceValue(entity.getPriceValue());
        propertyDTO.setPriceText(entity.getPriceText());

        return propertyDTO;
    }

    public static Property toEntity(PropertyCreateDTO propertyCreateDTO, String id) {
        Property property = toEntity(propertyCreateDTO);
        property.setId(id);
        return property;
    }
}

 */


package com.propydis.studio.dto.mongodb.mapper;

import com.propydis.studio.dto.mongodb.PropertyCreateDTO;
import com.propydis.studio.dto.mongodb.PropertyDTO;
import com.propydis.studio.model.mongodb.Photo;
import com.propydis.studio.model.mongodb.Property;

import java.util.List;
import java.util.stream.Collectors;

public class PropertyMapper {

    public static Property toEntity(PropertyCreateDTO dto, List<Photo> photos) {
        Property property = new Property();
        property.setName(dto.getName());
        property.setDescription(dto.getDescription());
        property.setPriceValue(dto.getPriceValue());
        property.setPriceText(dto.getPriceText());

        List<String> photoIds = photos.stream()
                .map(Photo::getId)
                .collect(Collectors.toList());
        property.setPhotoIds(photoIds);

        return property;
    }

    public static Property toEntity(PropertyCreateDTO dto, List<Photo> photos, String id) {
        Property property = toEntity(dto, photos);
        property.setId(id);
        return property;
    }

    public static PropertyDTO toDTO(Property property, List<Photo> photos) {
        PropertyDTO dto = new PropertyDTO();
        dto.setId(property.getId());
        dto.setName(property.getName());
        dto.setDescription(property.getDescription());
        dto.setPriceValue(property.getPriceValue());
        dto.setPriceText(property.getPriceText());
        dto.setCreatedAt(property.getCreatedAt());
        dto.setUpdatedAt(property.getUpdatedAt());
        dto.setPhotos(PhotoMapper.toDTOList(photos));
        return dto;
    }
}