package com.propydis.studio.infrastructure.persistence.mongodb.project.photo;

import com.propydis.studio.domain.project.photo.Photo;
import com.propydis.studio.domain.project.photo.repository.PhotoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PhotoMongoRepository implements PhotoRepository {

    private final PhotoDocumentRepository photoDocumentRepository;

    public PhotoMongoRepository(PhotoDocumentRepository photoDocumentRepository) {
        this.photoDocumentRepository = photoDocumentRepository;
    }

    @Override
    public Photo save(Photo photo) {
        PhotoDocument doc = PhotoDocumentMapper.toDocument(photo);
        return PhotoDocumentMapper.toDomain(photoDocumentRepository.save(doc));
    }

    @Override
    public List<Photo> saveAll(List<Photo> photos) {
        List<PhotoDocument> docs = photos.stream()
                .map(PhotoDocumentMapper::toDocument)
                .collect(Collectors.toList());
        return photoDocumentRepository.saveAll(docs).stream()
                .map(PhotoDocumentMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Photo> findById(String id) {
        return photoDocumentRepository.findById(id).map(PhotoDocumentMapper::toDomain);
    }

    @Override
    public List<Photo> findAllById(List<String> ids) {
        return photoDocumentRepository.findAllById(ids).stream()
                .map(PhotoDocumentMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAllById(List<String> ids) {
        photoDocumentRepository.deleteAllById(ids);
    }
}
