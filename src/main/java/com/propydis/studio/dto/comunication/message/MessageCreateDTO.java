package com.propydis.studio.dto.comunication.message;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MessageCreateDTO {

        private String chatId;
        private Long senderId;
        private Long receiverId;
        @NotBlank(message = "El contenido del mensaje no puede estar vacio")
        @Size(max = 1500, message = "El mensaje no puede superar los 1500 caracteres")
        private String content;

        public MessageCreateDTO() {}

        public String getChatId() {
            return chatId;
        }

        public void setChatId(String chatId) {
            this.chatId = chatId;
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

        public String getContent() {
                return content;
        }

        public void setContent(String content) {
                this.content = content;
        }

}
