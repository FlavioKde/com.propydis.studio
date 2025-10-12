package com.propydis.studio.infrastructure.persistence.mongodb.comunication.message;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageDocumentRepository extends MongoRepository<MessageDocument,String> {
    List<MessageDocument> findByChatIdOrderByCreatedAtDesc(String chatId);
}
