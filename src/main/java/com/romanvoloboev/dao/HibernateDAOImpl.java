package com.romanvoloboev.dao;

import com.romanvoloboev.model.Message;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author romanvoloboev
 */
@Transactional
public class HibernateDAOImpl implements MessageDAO {

    private final SessionFactory sessionFactory;

    public HibernateDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Message message) {
        sessionFactory.getCurrentSession().save(message);
    }

    @Override
    public void update(Message message) {
        sessionFactory.getCurrentSession().update(message);
    }

    @Override
    public void delete(Long id) {
        sessionFactory.getCurrentSession().createQuery("delete Message where id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Message> getAllMessages() {
        return sessionFactory.getCurrentSession().getNamedQuery(Message.SELECT_ALL_ORDER_BY_USER_NAME).list();
    }

    public Message getById(Long id) {
        return sessionFactory.getCurrentSession().get(Message.class, id);
    }
}
