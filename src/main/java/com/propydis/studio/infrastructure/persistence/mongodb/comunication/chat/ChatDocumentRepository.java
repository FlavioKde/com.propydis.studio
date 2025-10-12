package com.propydis.studio.infrastructure.persistence.mongodb.comunication.chat;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatDocumentRepository extends MongoRepository<ChatDocument, String> {
}
