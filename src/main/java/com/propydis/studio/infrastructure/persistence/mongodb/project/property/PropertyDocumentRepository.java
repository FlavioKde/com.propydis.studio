package com.propydis.studio.infrastructure.persistence.mongodb.project.property;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PropertyDocumentRepository extends MongoRepository<PropertyDocument, String> {
}
