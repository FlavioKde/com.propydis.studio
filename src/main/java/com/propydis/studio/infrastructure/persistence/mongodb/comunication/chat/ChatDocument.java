package com.propydis.studio.infrastructure.persistence.mongodb.comunication.chat;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "chat")
public class ChatDocument {

        @Id
        private String id;
        private String name;
        @CreatedDate
        private LocalDateTime createdAt;
        private Long senderId;
        private Long receiverId;


        public ChatDocument() {}


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
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


}
