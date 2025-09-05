package com.propydis.studio.service.mongodb;

import com.propydis.studio.exception.exceptions.NotFoundByIdException;
import com.propydis.studio.model.mongodb.Property;
import com.propydis.studio.repository.mongodb.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    private PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public Property save(Property property) {

        validatedPrice(property);
        return propertyRepository.save(property);

    }

    public Property update(Property property, String id) {
        Property existing = propertyRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException(id, "property"));

        existing.setName(property.getName());
        existing.setDescription(property.getDescription());
        existing.setPhotos(property.getPhotos());
        existing.setPropertyStatus(property.getPropertyStatus());
        existing.setPriceValue(property.getPriceValue());
        existing.setPriceText(property.getPriceText());
        validatedPrice(existing);

        return propertyRepository.save(existing);
    }

    public List<Property> findAll() {
        return propertyRepository.findAll();
    }

    public Property findById(String id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException(id, "property"));
    }

    public void deleteById(String id) {
        Property existing = propertyRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException(id, "property"));

        propertyRepository.delete(existing);
    }

    public void validatedPrice(Property property) {
        if (property.getPriceText() == null && property.getPriceValue() == null) {
            throw new IllegalArgumentException("Debe especificarse un precio o una descripción.");
        }
    }
}
