package com.propydis.studio.domain.comunication.message;

import java.util.List;
import java.util.Optional;

public interface MessageRepository {

        Message  save(Message message);
        List<Message> saveAll(List<Message> messages);
        Optional<Message> findById(String id);
        void deleteById(String id);
}
