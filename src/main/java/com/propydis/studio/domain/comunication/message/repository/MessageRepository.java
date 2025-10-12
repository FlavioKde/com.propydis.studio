package com.propydis.studio.domain.comunication.message.repository;

import com.propydis.studio.domain.comunication.message.Message;

import java.util.List;
import java.util.Optional;

public interface MessageRepository {

        Message save(Message message);
        List<Message> findAll();
        List<Message> saveAll(List<Message> messages);
        Optional<Message> findById(String id);
        List<Message> findByChatId(String chatId);
        void deleteById(String id);
}
