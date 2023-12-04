package com.projetCertif.dao.entityDto;
import com.projetCertif.dao.entity.Channel;
import lombok.*;

import java.util.Optional;

@Data
@Builder
public class ChannelDto {
    private int id;
    private String nom;

    // FUNCTION WITH AN OPTIONAL PARAMETER OBJECT CHANNEL
    public static  ChannelDto fromEntity(Optional<Channel> channel) {
        if (channel == null){
            //TODO throw an exception
            return null;
        }
        return ChannelDto.builder()
                .id(channel.get().getId())
                .nom(channel.get().getName())
                .build();
    }

    public static Channel toEntity(ChannelDto channelDto){
        if(channelDto == null){
            //TODO throw an exception
            return  null;
        }
        Channel channel= new Channel();
        channel.setId(channelDto.getId());
        channel.setName(channelDto.getNom());
        return channel;

    }

}