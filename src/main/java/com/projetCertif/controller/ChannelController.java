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
@RequestMapping("api/users")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @GetMapping
    public ResponseEntity<List<Channel>> getAllChannels() {
        return ResponseEntity.ok(channelService.getAllChannels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Channel> getChannelById(@PathVariable Long id) {
        Optional<Channel> channel = channelService.getChannelById(id);
        return channel.isPresent()? ResponseEntity.ok(channel.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Channel> addChannel(@RequestBody Channel channel) {
        Channel addedChannel = channelService.addChannel(channel);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedChannel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChannel(@PathVariable Long id) {
        channelService.deleteChannel(id);
        return ResponseEntity.noContent().build();
    }
}
