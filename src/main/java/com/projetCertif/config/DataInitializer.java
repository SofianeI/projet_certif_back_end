package com.projetCertif.config;

import com.projetCertif.dao.entity.Channel;
import com.projetCertif.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final ChannelService channelService;

    @Autowired
    public DataInitializer(ChannelService channelService) {
        this.channelService = channelService;
    }

    public void initChannelGeneral(){
        if(!channelService.checkChannelExists("General")){
            Channel generalChannel = new Channel();
            generalChannel.setName("General");
            channelService.addChannel(generalChannel);
        }
    }
}
