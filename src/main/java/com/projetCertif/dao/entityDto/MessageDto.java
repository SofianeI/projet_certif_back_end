package com.projetCertif.dao.entityDto;

import com.projetCertif.dao.entity.Channel;
import com.projetCertif.dao.entity.Message;
import com.projetCertif.dao.entity.User;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Optional;


@Data
@Builder
public class MessageDto {

    private int id;
    private String contenu;
    private UserDto utilisateur;
    private ChannelDto canal;
    private LocalDateTime date;

      // FUNCTION WITH AN OPTIONAL PARAMETER OBJECT MESSAGE
    public static  MessageDto fromEntity(Optional<Message> message) {
        if (message == null){
            //TODO throw an exception
            return null;
        }
        return MessageDto.builder()
                .id(message.get().getId())
                .contenu(message.get().getContent())
                .canal(ChannelDto.fromEntity(Optional.ofNullable(message.get().getChannel())))
                .utilisateur(UserDto.fromEntity(Optional.ofNullable(message.get().getUser())))
                .date(message.get().getDate())
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
        if(String.valueOf(messageDto.getUtilisateur().getId()) != null){
            message.setUser(new User(messageDto.getUtilisateur().getId(),null,null,null));
        }
        if(String.valueOf(messageDto.getCanal().getId()) != null){
            message.setChannel(new Channel(messageDto.getCanal().getId(),null));
        }
        message.setDate(messageDto.getDate());
        return message;
    }


}