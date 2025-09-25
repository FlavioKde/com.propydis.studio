package com.propydis.studio.infrastructure.persistence.mongodb.project.photo;

import org.springframework.data.mongodb.repository.MongoRepository;



public interface PhotoDocumentRepository extends MongoRepository<PhotoDocument, String> {
}
