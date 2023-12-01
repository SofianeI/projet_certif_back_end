package com.projetCertif.controller;

import com.projetCertif.dao.entity.Channel;
import com.projetCertif.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @GetMapping("channels")
    public ResponseEntity<List<Channel>> getAllChannels() {
        return ResponseEntity.ok(channelService.getAllChannels());
    }

    @GetMapping("channels/{id}")
    public ResponseEntity<Channel> getChannelById(@PathVariable Long id) {
        Optional<Channel> channel = channelService.getChannelById(id);
        return channel.isPresent()? ResponseEntity.ok(channel.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping("channels")
    public ResponseEntity<Channel> addChannel(@RequestBody Channel channel) {
        Channel addedChannel = channelService.addChannel(channel);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedChannel);
    }

    @DeleteMapping("channels/{id}")
    public ResponseEntity<Void> deleteChannel(@PathVariable Long id) {
        channelService.deleteChannel(id);
        return ResponseEntity.noContent().build();
    }

    // UPDATE CHANNEL
    @PutMapping("channels")
    public ResponseEntity update(@RequestBody Channel channel) {

        Channel channelUpdate =  channelService.updateChannel(channel);
        if(channelUpdate !=null)
            return ResponseEntity.status(204).body("Channel successfully updated");
        else
             return ResponseEntity.status(403).build();

    }
}
