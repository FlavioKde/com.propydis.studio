package com.propydis.studio.infrastructure.persistence.mongodb.comunication.message;

import com.propydis.studio.domain.comunication.message.Message;
import com.propydis.studio.domain.comunication.message.repository.MessageRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MessageMongoRepository implements MessageRepository {

            private final MessageDocumentRepository messageDocumentRepository;

            public MessageMongoRepository(MessageDocumentRepository messageDocumentRepository) {
                this.messageDocumentRepository = messageDocumentRepository;
            }

            @Override
            public Message save(Message message) {
                MessageDocument messageDocument = MessageDocumentMapper.toDocument(message);

                MessageDocument savedDocument = messageDocumentRepository.save(messageDocument);

                return MessageDocumentMapper.toDomain(savedDocument);

            }

            @Override
            public List<Message> findAll(){
                return messageDocumentRepository.findAll().stream().map(MessageDocumentMapper::toDomain).collect(Collectors.toList());

            }

            @Override
            public Optional<Message> findById(String id) {
                return messageDocumentRepository.findById(id).map(MessageDocumentMapper::toDomain);
            }

            @Override
            public void deleteById(String id) {
                messageDocumentRepository.deleteById(id);
            }

            @Override
            public List<Message> saveAll(List<Message> messages) {
                List<MessageDocument> messageDocuments = messages.stream()
                        .map(MessageDocumentMapper::toDocument)
                        .collect(Collectors.toList());

                return messageDocumentRepository.saveAll(messageDocuments)
                        .stream().map(MessageDocumentMapper::toDomain)
                        .collect(Collectors.toList());
            }

            @Override
            public List<Message> findByChatId(String chatId) {
                return messageDocumentRepository.findByChatIdOrderByCreatedAtDesc(chatId)
                        .stream()
                        .map(MessageDocumentMapper::toDomain)
                        .collect(Collectors.toList());
            }

}
