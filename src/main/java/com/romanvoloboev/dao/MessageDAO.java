package com.romanvoloboev.dao;

import com.romanvoloboev.model.Message;

import java.util.List;

/**
 * @author romanvoloboev
 */
public interface MessageDAO {
    void save(Message message);
    void update(Message message);
    void delete(Long id);
    List<Message> getAllMessages();
    Message getById(Long id);
}
