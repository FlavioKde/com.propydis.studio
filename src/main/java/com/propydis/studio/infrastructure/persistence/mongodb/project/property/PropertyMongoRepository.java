package com.propydis.studio.infrastructure.persistence.mongodb.project.property;

import com.propydis.studio.domain.project.photo.repository.PhotoRepository;
import com.propydis.studio.domain.project.property.Property;
import com.propydis.studio.domain.project.property.repository.PropertyRepository;
import java.util.Optional;

import com.propydis.studio.shared.exception.exceptions.NotFoundByIdException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PropertyMongoRepository implements PropertyRepository {

    private final PropertyDocumentRepository propertyDocumentRepository;
    private final PhotoRepository photoRepository;



public PropertyMongoRepository(PropertyDocumentRepository propertyDocumentRepository, PhotoRepository photoRepository) {
    this.propertyDocumentRepository = propertyDocumentRepository;
    this.photoRepository = photoRepository;
}
    @Override
    public Property save(Property property) {
        PropertyDocument propertyDocument = PropertyDocumentMapper.toDocument(property);
        PropertyDocument savedDocument = propertyDocumentRepository.save(propertyDocument);

        return PropertyDocumentMapper.toDomain(savedDocument);
    }

    @Override
    public List<Property> findAll(){
        return propertyDocumentRepository.findAll().stream().map(PropertyDocumentMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Property> findById(String id){
        return propertyDocumentRepository.findById(id).map(PropertyDocumentMapper::toDomain);
    }


    @Override
    public void deleteById(String id){

    PropertyDocument existing = propertyDocumentRepository.findById(id)
            .orElseThrow(() -> new NotFoundByIdException(id, "property"));

    photoRepository.deleteAllById(existing.getPhotoIds());

    propertyDocumentRepository.deleteById(id);
    }

}
