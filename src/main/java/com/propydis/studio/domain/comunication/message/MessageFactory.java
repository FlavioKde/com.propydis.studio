package com.propydis.studio.domain.comunication.message;

import java.time.LocalDateTime;
import java.util.UUID;

public class MessageFactory {

            public static Message createMessage(String chatId, Long senderId, Long receiverId, String content) {

                if (content == null ||  content.isBlank()) {
                    throw new IllegalArgumentException("Content no puede ser nulo o vacio");
                }

                if(senderId.equals(receiverId)){
                    throw new IllegalArgumentException("Sender id no puede ser igual");

                }

                Message message = new Message();
                message.setId(UUID.randomUUID().toString());
                message.setSenderId(senderId);
                message.setReceiverId(receiverId);
                message.setContent(content);
                message.setCreatedAt(LocalDateTime.now());
                return message;

            }
}
