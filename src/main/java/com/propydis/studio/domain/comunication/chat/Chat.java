package com.propydis.studio.domain.comunication.chat;

import java.time.LocalDateTime;

public class Chat {


        private String id;
        private String name;
        private LocalDateTime createdAt;
        private Long senderId;
        private Long receiverId;


        public Chat(String id, String name, LocalDateTime createdAt, Long senderId, Long receiverId) {
            this.id = id;
            this.name = name;
            this.createdAt = createdAt;
            this.senderId = senderId;
            this.receiverId = receiverId;

        }

        public Chat(){

        }

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
