package com.romanvoloboev.service;

import com.romanvoloboev.dao.MessageDAO;
import com.romanvoloboev.dto.MessageDTO;
import com.romanvoloboev.model.Message;
import com.romanvoloboev.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author romanvoloboev
 */
@Service
public class MessageService {
    private static final Logger log = LoggerFactory.getLogger(MessageService.class);
    private final MessageDAO messageDAO;

    @Autowired
    public MessageService(@Qualifier("messageDAO") MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public void updateMessage(long messageId, MessageDTO messageDTO) {
        log.info("Updating message with ID: {}", messageId);
        String text = messageDTO.getText();
        if (text == null || text.equals("")) throw new IllegalArgumentException("Message text can't be empty");

        Message message = messageDAO.getById(messageId);
        if (message == null) throw new IllegalArgumentException("Message not found for this id.");
        message.setText(text);
        messageDAO.update(message);
    }

    public List<MessageDTO> loadAllMessages() {
        log.info("Getting all messages...");
        List<MessageDTO> result = new ArrayList<>();
        List<Message> all = messageDAO.getAllMessages();
        if (all != null) {
            for (Message message : all) {
                result.add(new MessageDTO(message.getId(), message.getUser().getName(), message.getText()));
            }
            return result;
        } else {
            return Collections.emptyList();
        }
    }

    public void saveMessage(MessageDTO messageDTO) {
        log.info("Saving new message...");
        String userName = messageDTO.getUserName();
        String text = messageDTO.getText();
        if (userName == null || userName.equals("")) throw new IllegalArgumentException("Username can't be empty");
        if (text == null || text.equals("")) throw new IllegalArgumentException("Message text can't be empty");

        Message message = new Message(messageDTO.getText(), new User(userName));
        messageDAO.save(message);
    }

    public void deleteMessage(long messageId) {
        log.info("Deleting message with ID: {}", messageId);
        messageDAO.delete(messageId);
    }
}
