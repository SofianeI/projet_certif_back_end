package com.projetCertif.service;

import com.projetCertif.dao.entity.Message;
import com.projetCertif.dao.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*****************************************************************
 // Message Service
 // getAllMessages, getMessageById, addMessage, deleteMessage -done
 // none tested yet
 // later add updateMessage
 //
 ******************************************************************/
@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(Long id){
        return messageRepository.findById(id);
    }

    public Message addMessage(Message message){
        return messageRepository.save(message);
    }

    public void deleteMessage(Long id){
        messageRepository.deleteById(id);
    }
}
