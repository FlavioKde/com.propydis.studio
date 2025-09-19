package com.propydis.studio.shared.mapper;

import com.propydis.studio.domain.project.Photo;
import com.propydis.studio.dto.project.PhotoDTO;

import java.util.List;
import java.util.stream.Collectors;

public class PhotoDtoMapper {

    public static Photo toDomain(PhotoDTO dto) {
        if (dto == null) return null;
        Photo photo = new Photo();
        photo.setId(dto.getId());
        photo.setUrl(dto.getUrl());
        photo.setAltText(dto.getAltText());
        photo.setPublicId(dto.getPublicId());
        return photo;
    }

    public static PhotoDTO toDto(Photo photo) {
        if (photo == null) return null;
        PhotoDTO dto = new PhotoDTO();
        dto.setId(photo.getId());
        dto.setUrl(photo.getUrl());
        dto.setAltText(photo.getAltText());
        dto.setPublicId(photo.getPublicId());
        return dto;
    }

    public static List<PhotoDTO> toDtoList(List<Photo> photos) {
        return photos.stream()
                .map(PhotoDtoMapper::toDto)
                .collect(Collectors.toList());
    }
}

