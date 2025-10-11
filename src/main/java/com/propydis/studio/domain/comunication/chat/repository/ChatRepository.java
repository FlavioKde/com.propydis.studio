package com.propydis.studio.domain.comunication.chat.repository;

import com.propydis.studio.domain.comunication.chat.Chat;

import java.util.List;

public interface ChatRepository {

        Chat save(Chat chat);
        List<Chat> findAll();
        Chat findById(String id);
        void deleteById(String id);
}
