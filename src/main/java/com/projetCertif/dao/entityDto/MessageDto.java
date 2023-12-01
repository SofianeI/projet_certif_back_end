package com.projetCertif.dao.entityDto;

import com.projetCertif.dao.entity.Channel;
import com.projetCertif.dao.entity.Message;
import com.projetCertif.dao.entity.User;
import lombok.*;
import java.time.LocalDateTime;



@Data
@Builder
public class MessageDto {

    private int id;
    private String contenu;
    private User utilisateur;
    private Channel canal;
    private LocalDateTime date;
     public static  MessageDto fromEntity(Message message) {
        if (message == null){
            //TODO throw an exception
            return null;
        }
        return MessageDto.builder()
                .id(message.getId())
                .contenu(message.getContent())
                .utilisateur(message.getUser())
                .canal(message.getChannel())
                .date(message.getDate())
                .build();
    }

    public static Message toEntity(MessageDto messageDto){
        if(messageDto == null){
            //TODO throw an exception
            return  null;
        }
        Message message= new Message();
        message.setId(messageDto.getId());
        message.setContent(messageDto.getContenu());
        message.setUser(messageDto.getUtilisateur());
        message.setChannel(messageDto.getCanal());
        message.setDate(messageDto.getDate());
        return message;
    }


}