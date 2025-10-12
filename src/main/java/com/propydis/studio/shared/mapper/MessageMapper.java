package com.propydis.studio.shared.mapper;


import com.propydis.studio.domain.comunication.message.Message;
import com.propydis.studio.dto.comunication.message.MessageCreateDTO;
import com.propydis.studio.dto.comunication.message.MessageDTO;

public class MessageMapper {


            public static MessageDTO toDTO(Message message) {
                if(message == null) {
                    return null;
                }
                MessageDTO messageDTO = new MessageDTO();

                messageDTO.setId(message.getId());
                messageDTO.setContent(message.getContent());
                messageDTO.setCreatedAt(message.getCreatedAt());
                messageDTO.setSenderId(message.getSenderId());
                messageDTO.setReceiverId(message.getReceiverId());
                messageDTO.setChatId(message.getChatId());

                return messageDTO;

            }

            public static Message toDomain(MessageCreateDTO messageCreateDTO) {
                if(messageCreateDTO==null) {
                    return null;
                }
                Message message = new Message();

                message.setContent(messageCreateDTO.getContent());
                message.setSenderId(messageCreateDTO.getSenderId());
                message.setReceiverId(messageCreateDTO.getReceiverId());
                message.setChatId(messageCreateDTO.getChatId());

                return message;
            }
}
