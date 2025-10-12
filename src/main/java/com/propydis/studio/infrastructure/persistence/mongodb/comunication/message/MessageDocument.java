package com.propydis.studio.infrastructure.persistence.mongodb.comunication.message;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "message")
public class MessageDocument {

        @Id
        private String id;
        private String content;
        @CreatedDate
        private LocalDateTime createdAt;

        private Long senderId;
        private Long receiverId;
        private String chatId;

        public MessageDocument() {

        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }

        public Long getSenderId() {
            return senderId;
        }

        public void setSenderId(Long senderId) {
            this.senderId = senderId;
        }

        public Long getReceiverId() {
            return receiverId;
        }

        public void setReceiverId(Long receiverId) {
            this.receiverId = receiverId;
        }

        public String getChatId() {
            return chatId;
        }
        public void setChatId(String chatId) {
            this.chatId = chatId;
        }

}

