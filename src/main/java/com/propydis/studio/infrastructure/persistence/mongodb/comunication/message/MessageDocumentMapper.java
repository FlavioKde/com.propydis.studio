package com.propydis.studio.infrastructure.persistence.mongodb.comunication.message;

import com.propydis.studio.domain.comunication.message.Message;

public class MessageDocumentMapper {

        public static Message toDomain(MessageDocument messageDocument) {
            if (messageDocument == null){
                return null;
            }
            Message message = new Message();
            message.setId(messageDocument.getId());
            message.setContent(messageDocument.getContent());
            message.setCreatedAt(messageDocument.getCreatedAt());
            message.setSenderId(messageDocument.getSenderId());
            message.setReceiverId(messageDocument.getReceiverId());
            message.setChatId(messageDocument.getChatId());

            return message;

        }

        public static MessageDocument toDocument(Message message) {
            if (message == null){
                return null;
            }
            MessageDocument messageDocument = new MessageDocument();
            messageDocument.setId(message.getId());
            messageDocument.setContent(message.getContent());
            messageDocument.setCreatedAt(message.getCreatedAt());
            messageDocument.setSenderId(message.getSenderId());
            messageDocument.setReceiverId(message.getReceiverId());
            messageDocument.setChatId(message.getChatId());

            return messageDocument;
        }
}
