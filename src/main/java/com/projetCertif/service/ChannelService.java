package com.projetCertif.service;

import com.projetCertif.dao.entity.Channel;
import com.projetCertif.dao.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ChannelService {

    @Autowired
    private ChannelRepository channelRepository;

    public List<Channel> getAllChannels(){
        return channelRepository.findAll();
    }

    public Optional<Channel> getChannelById(Long id){
        return channelRepository.findById(id);
    }

    public Channel addChannel(Channel channel){
        return channelRepository.save(channel);
    }

    public void deleteChannel(Long id){
        channelRepository.deleteById(id);
    }

    public Channel updateChannel(Channel channel) {
        return channelRepository.save(channel);
    }

    public Boolean checkChannelExists(String name){
        Optional<Channel> channel = Optional.ofNullable(channelRepository.findByName(name));
        return channel.isPresent();
    }

}
