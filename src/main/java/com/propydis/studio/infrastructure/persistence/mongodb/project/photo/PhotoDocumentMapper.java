package com.propydis.studio.infrastructure.persistence.mongodb.project.photo;

import com.propydis.studio.domain.project.photo.Photo;

public class PhotoDocumentMapper {

    public static Photo toDomain(PhotoDocument doc) {
        if (doc == null) return null;
        Photo photo = new Photo();
        photo.setId(doc.getId());
        photo.setUrl(doc.getUrl());
        photo.setAltText(doc.getAltText());
        photo.setPublicId(doc.getPublicId());
        return photo;
    }

    public static PhotoDocument toDocument(Photo photo) {
        if (photo == null) return null;
        PhotoDocument doc = new PhotoDocument();
        doc.setId(photo.getId());
        doc.setUrl(photo.getUrl());
        doc.setAltText(photo.getAltText());
        doc.setPublicId(photo.getPublicId());
        return doc;
    }
}
