package com.enterprise.ent_invest_backend.Controller;
import com.enterprise.ent_invest_backend.Model.Message;
import com.enterprise.ent_invest_backend.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping("/addMessage")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        Message createdMessage = messageService.createMessage(message);
        return ResponseEntity.ok(createdMessage);
    }

    @GetMapping("/getMessageById/{id}")
    public ResponseEntity<Optional<Message>> getMessageById(@PathVariable("id") String messageId) {
        Optional<Message> message = messageService.getMessageById(messageId);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/getMessageByUserId/{id}")
    public ResponseEntity<List<Message>> getMessageByUserId(@PathVariable("id") String userId) {
        List<Message> message = messageService.getMessageByUserId(userId);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/allMessages")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable("id") String messageId, @RequestBody Message messageDetails) {
        Message updatedMessage = messageService.updateMessage(messageId, messageDetails);
        return ResponseEntity.ok(updatedMessage);
    }


    @PutMapping("/updateMessageByStatus/{id}")
    public ResponseEntity<Message> updateMessageByStatus(@PathVariable("id") String messageId) {
        Message updatedMessage = messageService.updateStatus(messageId);
        return ResponseEntity.ok(updatedMessage);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable("id") String messageId) {
        messageService.deleteMessage(messageId);
        return ResponseEntity.ok("Message with ID " + messageId + " has been deleted successfully.");
    }

}
