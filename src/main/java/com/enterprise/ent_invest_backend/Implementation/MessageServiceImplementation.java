package com.enterprise.ent_invest_backend.Implementation;

import com.enterprise.ent_invest_backend.Model.Message;
import com.enterprise.ent_invest_backend.Repository.MessageRepository;
import com.enterprise.ent_invest_backend.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImplementation implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public List<Message> getMessageByUserId(String userId) {
        return messageRepository.findByUserId(userId);
    }

    @Override
    public Optional<Message> getMessageById(String messageId) {
        return messageRepository.findById(messageId);
    }

    @Override
    public Message updateMessage(String messageId, Message message) {
        Optional<Message> existingMessageOptional = messageRepository.findById(messageId);
        if (existingMessageOptional.isEmpty()) {
            throw new RuntimeException("No message found with ID: " + messageId);
        }

        Message existingMessage = existingMessageOptional.get();
        if (message.getUserId() != null) {
            existingMessage.setUserId(message.getUserId());
        }
        if (message.getSubject() != null) {
            existingMessage.setSubject(message.getSubject());
        }
        if (message.getContent() != null) {
            existingMessage.setContent(message.getContent());
        }

        return messageRepository.save(existingMessage);
    }

    @Override
    public void deleteMessage(String messageId) {
        messageRepository.deleteById(messageId);
    }

    @Override
    public Message updateStatus(String messageId) {
        Optional<Message> existingMessage = messageRepository.findById(messageId);
        if (existingMessage.isEmpty()) {
            throw new RuntimeException("No message found with ID: " + messageId);
        }
        Message message = existingMessage.get();
        message.setMessageStatus("seen");

        return messageRepository.save(message);
    }

}
