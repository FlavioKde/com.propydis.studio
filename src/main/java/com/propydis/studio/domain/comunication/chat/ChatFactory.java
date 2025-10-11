package com.propydis.studio.domain.comunication.chat;

import java.time.LocalDateTime;
import java.util.UUID;

public class ChatFactory {

        public static Chat createChat(Long senderId, Long receiverId, String name){
            if (senderId.equals(receiverId)) {
                throw new IllegalArgumentException("senderId y receiverId no pueden ser iguales ");

            }

            Chat chat = new Chat();
            chat.setId(UUID.randomUUID().toString());
            chat.setSenderId(senderId);
            chat.setReceiverId(receiverId);
            chat.setName(name != null && ! name.isBlank() ? name : "Chat-" + senderId + "-" + receiverId);
            chat.setCreatedAt(LocalDateTime.now());

            return chat;

        }
}
