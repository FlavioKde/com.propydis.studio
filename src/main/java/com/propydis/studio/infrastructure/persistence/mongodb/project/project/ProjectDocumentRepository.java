package com.propydis.studio.infrastructure.persistence.mongodb.project.project;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectDocumentRepository extends MongoRepository<ProjectDocument, String> {
}
