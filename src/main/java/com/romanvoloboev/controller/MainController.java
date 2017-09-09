package com.romanvoloboev.controller;

import com.romanvoloboev.dto.ErrorResponseDTO;
import com.romanvoloboev.dto.MessageDTO;
import com.romanvoloboev.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author romanvoloboev
 */
@Controller
public class MainController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    private final MessageService messageService;

    @Autowired
    public MainController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loadPage() {
        return "index";
    }

    @RequestMapping(value = "/message", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity addMessage(@RequestBody MessageDTO messageDTO) {
        try {
            messageService.saveMessage(messageDTO);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (IllegalArgumentException e) {
            log.info("Can't add new message. {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public ResponseEntity loadMessages() {
        List<MessageDTO> messages = messageService.loadAllMessages();
        return ResponseEntity.status(HttpStatus.OK).body(messages);
    }

    @RequestMapping(value = "/message/{messageId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateMessage(@RequestBody MessageDTO messageDTO,
                                        @PathVariable long messageId) {
        try {
            messageService.updateMessage(messageId, messageDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (IllegalArgumentException e) {
            log.info("Can't update message. {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @RequestMapping(value = "/message/{messageId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteMessage(@PathVariable long messageId) {
        messageService.deleteMessage(messageId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
