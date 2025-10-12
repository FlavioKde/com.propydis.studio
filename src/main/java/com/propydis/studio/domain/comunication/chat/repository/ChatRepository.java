package com.propydis.studio.domain.comunication.chat.repository;

import com.propydis.studio.domain.comunication.chat.Chat;

import java.util.List;
import java.util.Optional;

public interface ChatRepository {

        Chat save(Chat chat);
        List<Chat> findAll();
        Optional<Chat> findById(String id);
        void deleteById(String id);
}
