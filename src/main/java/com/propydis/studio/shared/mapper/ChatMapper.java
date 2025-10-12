package com.propydis.studio.shared.mapper;

import com.propydis.studio.domain.comunication.chat.Chat;
import com.propydis.studio.dto.comunication.chat.ChatCreateDTO;
import com.propydis.studio.dto.comunication.chat.ChatDTO;

public class ChatMapper {

            public static ChatDTO toDTO(Chat chat) {
                if(chat==null){
                    return null;
                }
                ChatDTO chatDTO = new ChatDTO();

                chatDTO.setId(chat.getId());
                chatDTO.setName(chat.getName());
                chatDTO.setCreatedAt(chat.getCreatedAt());
                chatDTO.setReceiverId(chat.getReceiverId());
                chatDTO.setSenderId(chat.getSenderId());

                return chatDTO;

            }

            public static Chat toDomain(ChatCreateDTO chatCreateDTO) {
                if(chatCreateDTO==null){
                    return null;
                }
                Chat chat = new Chat();

                chat.setName(chatCreateDTO.getName());
                chat.setReceiverId(chatCreateDTO.getReceiverId());
                chat.setSenderId(chatCreateDTO.getSenderId());

                return chat;
            }
}
