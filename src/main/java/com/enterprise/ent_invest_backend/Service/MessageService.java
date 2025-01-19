package com.enterprise.ent_invest_backend.Service;

import com.enterprise.ent_invest_backend.Model.Message;
import java.util.List;
import java.util.Optional;

public interface MessageService {
    public Message createMessage(Message message);
    public List<Message> getAllMessages();
    public List<Message> getMessageByUserId(String userId);
    public Optional<Message> getMessageById(String messageId);
    public Message updateMessage(String messageId, Message message);
    public void deleteMessage(String messageId);
    public Message updateStatus(String messageId);
}
