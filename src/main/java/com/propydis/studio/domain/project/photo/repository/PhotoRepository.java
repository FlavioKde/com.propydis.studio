package com.propydis.studio.domain.project.photo.repository;

import com.propydis.studio.domain.project.photo.Photo;

import java.util.List;
import java.util.Optional;

public interface PhotoRepository {

        Photo save(Photo photo);
        List<Photo> saveAll(List<Photo> photos);
        Optional<Photo> findById(String id);
        List<Photo> findAllById(List<String> ids);
        void deleteAllById(List<String> ids);
}
