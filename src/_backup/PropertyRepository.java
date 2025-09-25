package com.propydis.studio.repository.mongodb;

import com.propydis.studio.domain.project.property.Property;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PropertyRepository extends MongoRepository<Property, String> {
}
