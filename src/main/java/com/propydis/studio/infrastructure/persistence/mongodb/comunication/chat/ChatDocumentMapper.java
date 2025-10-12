package com.propydis.studio.infrastructure.persistence.mongodb.comunication.chat;

import com.propydis.studio.domain.comunication.chat.Chat;

public class ChatDocumentMapper {

            public static Chat toDomain(ChatDocument chatDocument) {
                if (chatDocument == null) {
                    return null;
                }
                Chat chat = new Chat();
                chat.setId(chatDocument.getId());
                chat.setName(chatDocument.getName());
                chat.setCreatedAt(chatDocument.getCreatedAt());
                chat.setSenderId(chatDocument.getSenderId());
                chat.setReceiverId(chatDocument.getReceiverId());

                return chat;
            }

            public static ChatDocument toDocument(Chat chat) {
                if (chat == null) {
                    return null;
                }
                ChatDocument chatDocument = new ChatDocument();
                chatDocument.setId(chat.getId());
                chatDocument.setName(chat.getName());
                chatDocument.setCreatedAt(chat.getCreatedAt());
                chatDocument.setSenderId(chat.getSenderId());
                chatDocument.setReceiverId(chat.getReceiverId());

                return chatDocument;
            }

}
