package com.propydis.studio.dto.mongodb.mapper;

import com.propydis.studio.dto.mongodb.PhotoDTO;
import com.propydis.studio.model.mongodb.Photo;

import java.util.List;
import java.util.stream.Collectors;

public class PhotoMapper {

    public static Photo toEntity(PhotoDTO photoDTO) {
        if (photoDTO == null) {
            return null;
        }
        Photo photo = new Photo();
        photo.setId(photoDTO.getId());
        photo.setUrl(photoDTO.getUrl());
        photo.setAltText(photoDTO.getAltText());
        photo.setPublicId(photoDTO.getPublicId());

        return photo;
    }

    public static PhotoDTO toDTO(Photo photo) {
        if (photo == null) {
            return null;
        }
        PhotoDTO photoDTO = new PhotoDTO();
        photoDTO.setId(photo.getId());
        photoDTO.setUrl(photo.getUrl());
        photoDTO.setAltText(photo.getAltText());
        photoDTO.setPublicId(photo.getPublicId());

        return photoDTO;
    }

    public static List<PhotoDTO> toDTOList(List<Photo> photos) {
        return photos.stream()
                .map(PhotoMapper::toDTO)
                .collect(Collectors.toList());
    }
}
