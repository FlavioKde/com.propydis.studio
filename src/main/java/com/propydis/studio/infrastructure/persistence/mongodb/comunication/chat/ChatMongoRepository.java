package com.propydis.studio.infrastructure.persistence.mongodb.comunication.chat;

import com.propydis.studio.domain.comunication.chat.Chat;
import com.propydis.studio.domain.comunication.chat.repository.ChatRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ChatMongoRepository implements ChatRepository {


            private final ChatDocumentRepository chatDocumentRepository;

            public ChatMongoRepository(ChatDocumentRepository chatDocumentRepository) {
                this.chatDocumentRepository = chatDocumentRepository;
            }

            @Override
            public Chat save(Chat chat) {
                ChatDocument chatDocument = ChatDocumentMapper.toDocument(chat);

                ChatDocument savedChat = chatDocumentRepository.save(chatDocument);

                return ChatDocumentMapper.toDomain(savedChat);
            }

            @Override
            public List<Chat> findAll(){
                return chatDocumentRepository.findAll().stream().map(ChatDocumentMapper::toDomain).collect(Collectors.toList());
            }


            @Override
            public Optional <Chat> findById(String id) {
                return chatDocumentRepository.findById(id).map(ChatDocumentMapper::toDomain);
            }

            @Override
            public void deleteById(String id) {
                chatDocumentRepository.deleteById(id);
            }


}
