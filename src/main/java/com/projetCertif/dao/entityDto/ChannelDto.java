package com.projetCertif.dao.entityDto;
import com.projetCertif.dao.entity.Channel;
import lombok.*;

@Data
@Builder
public class ChannelDto {
    private int id;
    private String nom;

    public static  ChannelDto fromEntity(Channel channel) {
        if (channel == null){
            //TODO throw an exception
            return null;
        }
        return ChannelDto.builder()
                .id(channel.getId())
                .nom(channel.getName())
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