package com.propydis.studio.domain.project.property.repository;

import com.propydis.studio.domain.project.property.Property;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository{

            Property save(Property property);
            Optional<Property> findById(String id);
            List<Property> findAll();
            void deleteById(String id);
}
