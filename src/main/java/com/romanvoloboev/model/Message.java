package com.romanvoloboev.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author romanvoloboev
 */
@NamedQueries({
        @NamedQuery(name = Message.SELECT_ALL_ORDER_BY_USER_NAME,
                query = "select new com.romanvoloboev.model.Message(m.id, m.text, m.user) from Message m inner join m.user as u order by u.name")
})

@Entity
@Table(name = "message")
public class Message implements Serializable {
    public static final String SELECT_ALL_ORDER_BY_USER_NAME = "select_all_msg_order_by_user_name";

    private Long id;
    private String text;
    private User user;

    public Message() {
    }

    public Message(String text, User user) {
        this.text = text;
        this.user = user;
    }

    public Message(Long id, String text, User user) {
        this.id = id;
        this.text = text;
        this.user = user;
    }

    public Message(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "text", nullable = false)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;

        Message message = (Message) o;

        return getId().equals(message.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", user=" + user +
                '}';
    }
}
