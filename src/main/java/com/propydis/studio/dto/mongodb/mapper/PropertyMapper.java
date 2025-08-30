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
        return propertyDTO;
    }
}