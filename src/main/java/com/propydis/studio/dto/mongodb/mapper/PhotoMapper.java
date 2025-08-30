package com.propydis.studio.dto.mongodb.mapper;

import com.propydis.studio.dto.mongodb.PhotoDTO;
import com.propydis.studio.model.mongodb.Photo;

public class PhotoMapper {

    public static Photo toEntity(PhotoDTO photoDTO) {
        if (photoDTO == null) {
            return null;
        }
        Photo photo = new Photo();
        photo.setId(photoDTO.getId());
        photo.setUrl(photoDTO.getUrl());
        photo.setAltText(photoDTO.getAltText());

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

        return photoDTO;
    }
}
