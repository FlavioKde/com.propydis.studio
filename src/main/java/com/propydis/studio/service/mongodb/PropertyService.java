package com.propydis.studio.service.mongodb;

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
        return propertyRepository.save(property);
    }

    public Property Update(Property property) {
        if (!propertyRepository.existsById(property.getId())) {
            throw new RuntimeException("Property not found");
        }
        return propertyRepository.save(property);
    }

    public List<Property> findAll() {
        return propertyRepository.findAll();
    }

    public Property findById(String id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found"));
    }

    public void deleteById(String id) {
        if (!propertyRepository.existsById(id)) {
            throw new RuntimeException("Property not found");
        }
        propertyRepository.deleteById(id);
    }
}
