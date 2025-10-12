package com.propydis.studio.dto.comunication.chat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ChatCreateDTO {

        private Long senderId;
        private Long receiverId;

        @NotBlank(message = "El campo del nombre no puede estar vacio")
        @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
        private String name;

        public ChatCreateDTO() {}

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
}
