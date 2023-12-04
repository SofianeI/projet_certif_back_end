package com.projetCertif.dao.entityDto;

import com.projetCertif.dao.entity.Channel;
import com.projetCertif.dao.entity.Message;
import com.projetCertif.dao.entity.User;
import com.projetCertif.service.ChannelService;
import com.projetCertif.service.UserService;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Optional;


@Data
@Builder
public class MessageDto {

    private int id;
    private String contenu;
    private UserDto utilisateur;
    private Long idUtilisateur;
    private ChannelDto canal;
    private Long idCanal;
    private LocalDateTime date;

      // FUNCTION WITH AN OPTIONAL PARAMETER OBJECT MESSAGE
    public static  MessageDto fromEntity(Optional<Message> message) throws Exception{
        if (message == null){
            // throw an exception
            throw new Exception(" the message are null");

        }
        return MessageDto.builder()
                .id(message.get().getId())
                .contenu(message.get().getContent())
                .canal(ChannelDto.fromEntity(Optional.ofNullable(message.get().getChannel())))
                .utilisateur(UserDto.fromEntity(Optional.ofNullable(message.get().getUser())))
                .date(message.get().getDate())
                .build();
    }


    public static Message toEntity(MessageDto messageDto, UserService userService, ChannelService channelService)throws Exception{

        if(messageDto == null){
             // throw an exception
            throw new Exception(" the message is null");

        }
        Message message= new Message();
        message.setId(messageDto.getId());
        message.setContent(messageDto.getContenu());


        // Check
        if (messageDto.getUtilisateur() != null && messageDto.getUtilisateur().getId() > 0) {
            message.setUser(UserDto.toEntity(messageDto.getUtilisateur()));
        } else {
            Optional<User> userTemp = userService.getUserById(messageDto.getIdUtilisateur());
            message.setUser(UserDto.toEntity(UserDto.fromEntity(userTemp)));
        }

        if (messageDto.getCanal() != null && messageDto.getCanal().getId() > 0) {
            message.setChannel(ChannelDto.toEntity(messageDto.getCanal()));
        } else {
            Optional<Channel> channelTemp = channelService.getChannelById(messageDto.getIdCanal());
            message.setChannel(ChannelDto.toEntity(ChannelDto.fromEntity(channelTemp)));
        }

        message.setDate(LocalDateTime.now());
        message.setDate(messageDto.getDate());
        return message;
    }


}