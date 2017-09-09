package com.romanvoloboev.dao;

import com.romanvoloboev.model.Message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author romanvoloboev
 */
public class CollectionsDAOImpl implements MessageDAO {
    private static final List<Message> messageList = new ArrayList<>();
    private static final AtomicLong idSequence = new AtomicLong(1);

    @Override
    public void save(Message message) {
        message.setId(idSequence.getAndIncrement());
        messageList.add(message);
    }

    @Override
    public void update(Message message) {
        for (Iterator<Message> iterator = messageList.iterator(); iterator.hasNext();) {
            Message oldMsg = iterator.next();
            if (oldMsg.getId().equals(message.getId())) {
                iterator.remove();
                break;
            }
        }
        messageList.add(message);
    }

    @Override
    public void delete(Long id) {
        for (Iterator<Message> iterator = messageList.iterator(); iterator.hasNext();) {
            Message message = iterator.next();
            if (message.getId().equals(id)) {
                iterator.remove();
                break;
            }
        }
    }

    @Override
    public List<Message> getAllMessages() {
        messageList.sort((o1, o2) -> o1.getUser().getName().compareToIgnoreCase(o2.getUser().getName()));
        return messageList;
    }

    @Override
    public Message getById(Long id) {
        for (Message msg : messageList) {
            if (msg.getId().equals(id)) {
                return msg;
            }
        }
        return null;
    }
}
