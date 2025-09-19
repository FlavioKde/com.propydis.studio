package com.propydis.studio.dto.mongodb.mapper;

import com.propydis.studio.dto.mongodb.PropertyCreateDTO;
import com.propydis.studio.dto.mongodb.PropertyDTO;
import com.propydis.studio.domain.project.Photo;
import com.propydis.studio.domain.project.Property;
import com.propydis.studio.shared.mapper.PhotoDtoMapper;

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
        dto.setPhotos(PhotoDtoMapper.toDtoList(photos));

        return dto;
    }
}