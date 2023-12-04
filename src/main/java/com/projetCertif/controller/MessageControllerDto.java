package com.projetCertif.controller;

import com.projetCertif.dao.entity.Message;
import com.projetCertif.dao.entityDto.MessageDto;
import com.projetCertif.service.ChannelService;
import com.projetCertif.service.MessageService;
import com.projetCertif.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class MessageControllerDto {

    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @Autowired
    private ChannelService channelService;

    // GET ALL MESSAGE
    @GetMapping("messages")
    public List<MessageDto> getAllMessages() throws Exception {
        List<Message> messages = messageService.getAllMessages();
        List<MessageDto> messageDtos = new ArrayList<>();
        for(Message msg : messages) {
            messageDtos.add(MessageDto.fromEntity(Optional.ofNullable(msg)));
        }
        return messageDtos;
    }

    //CREATE  NEW MESSAGE
    @PostMapping("messages")
    public ResponseEntity<Void> add(@RequestBody MessageDto dto)throws Exception {
        messageService.addMessage(MessageDto.toEntity(dto, userService, channelService));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //GET MESSAGE BY ID
    @GetMapping("messages/{id}")
    public ResponseEntity<MessageDto> getMessageById(@PathVariable Long id) throws Exception {
        Optional<Message> message = messageService.getMessageById(id);
        MessageDto messageDto = MessageDto.fromEntity(message);
        if(messageDto == null) {
            return  ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(messageDto);
        }
    }

    //DELETE MESSAGE
    @DeleteMapping("messages/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        if(messageService.getMessageById(id).isEmpty() == true)
            return ResponseEntity.notFound().build();
        else {
            messageService.deleteMessage(id);
            return ResponseEntity.ok().build();
        }
    }

    // UPDATE MESSAGE
    @PutMapping("messages")
    public ResponseEntity update(@RequestBody MessageDto messageDto) throws Exception{
        Message msgUpdate = messageService.updateMessage(MessageDto.toEntity(messageDto, userService, channelService));

        if(msgUpdate !=null)
            return ResponseEntity.status(200).build();
        else
            return ResponseEntity.status(403).build();
    }
}
