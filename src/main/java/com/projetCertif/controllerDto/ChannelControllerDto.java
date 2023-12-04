package com.projetCertif.controllerDto;

import com.projetCertif.dao.entity.Channel;
import com.projetCertif.dao.entityDto.ChannelDto;
import com.projetCertif.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class ChannelControllerDto {

    @Autowired
    private ChannelService channelService;

    // GET ALL CHANNELS
    @GetMapping("channels")
    public List<ChannelDto> getAllChannels() throws Exception {
        List<Channel> channels = channelService.getAllChannels();
        List<ChannelDto> channelDtos = new ArrayList<>();
        for(Channel c : channels) {
            channelDtos.add(ChannelDto.fromEntity(Optional.ofNullable((c))));
        }
        return channelDtos;
    }

    //CREATE  NEW CHANNEL
    @PostMapping("channels")
    public void add(@RequestBody ChannelDto  dto) throws Exception {
        channelService.addChannel(ChannelDto.toEntity(dto));
    }

    //GET CHANNEL BY ID
    @GetMapping("channels/{id}")
    public Object getChannelById(@PathVariable Long id) throws Exception {
        Optional<Channel> channel = channelService.getChannelById(id);
        ChannelDto channelDto = ChannelDto.fromEntity(channel);
        if(channelDto == null) {
            return  ResponseEntity.notFound().build();
        } else {
            return channelDto;
        }
    }

    //DELETE CHANNEL
    @DeleteMapping("channels/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public ResponseEntity<Void> deleteChannel(@PathVariable Long id) {
        if(id == 1){
            return ResponseEntity.badRequest().build();
        }else {
            if(channelService.getChannelById(id).isEmpty() == true)
                return ResponseEntity.notFound().build();
            else {
                channelService.deleteChannel(id);
                return ResponseEntity.ok().build();
            }
        }
    }

    // UPDATE CHANNEL
    @PutMapping("channels")
    public ResponseEntity update(@RequestBody ChannelDto channelDto) throws Exception {
        Channel channelUpdate = channelService.updateChannel(ChannelDto.toEntity(channelDto));
        if(channelUpdate !=null)
            return ResponseEntity.status(200).build();
        else
            return ResponseEntity.status(403).build();

    }
}
