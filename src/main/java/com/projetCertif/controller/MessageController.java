package com.projetCertif.controller;

import com.projetCertif.dao.entity.Channel;
import com.projetCertif.dao.entity.Message;
import com.projetCertif.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.ok(messageService.getAllMessages());
    }

    @GetMapping("messages/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long id) {
        Optional<Message> message = messageService.getMessageById(id);
        return message.isPresent()? ResponseEntity.ok(message.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping("messages")
    public ResponseEntity<Message> addMessage(@RequestBody Message message) {
        Message addedMessage = messageService.addMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedMessage);
    }

    @DeleteMapping("messages/{id}")
    public ResponseEntity<Void>deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }

    // UPDATE MESSAGE
    @PutMapping("messages/{id}")
    public ResponseEntity update(@RequestBody Message message) {

        Message msgUpdate =  messageService.updateMessage(message);
        if(msgUpdate !=null)
            return ResponseEntity.status(204).body("Message successfully updated");
        else
            return ResponseEntity.status(403).build();

    }

}
