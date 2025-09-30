package com.propydis.studio.domain.message;

import java.time.LocalDateTime;

public class Message {

        private String id;
        private String content;
        private LocalDateTime createdAt;

        private Long senderId;
        private Long receiverId;
        private String chatId;


        public Message(String id, String content, LocalDateTime createdAt, Long senderId, Long receiverId, String chatId) {
            this.id = id;
            this.content = content;
            this.createdAt = createdAt;
            this.senderId = senderId;
            this.receiverId = receiverId;
            this.chatId = chatId;

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
