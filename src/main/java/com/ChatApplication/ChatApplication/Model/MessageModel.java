package com.ChatApplication.ChatApplication.Model;

import jakarta.persistence.*;
import java.util.Date;

/**
 * Entity class representing a message in the chat application.
 * Each message is associated with a sender and a receiver.
 */
@Table(name = "messages")
@Entity
public class MessageModel {

    /**
     * Unique identifier for each message.
     * It is auto-generated using IDENTITY strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The sender of the message, represented as a reference to a UserModel.
     * This establishes a many-to-one relationship with the UserModel entity.
     */
    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private UserModel sender;

    /**
     * The receiver of the message, represented as a reference to a UserModel.
     * This also establishes a many-to-one relationship with the UserModel entity.
     */
    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private UserModel receiver;

    /**
     * The actual content of the message.
     * This field is required (cannot be null).
     */
    @Column(nullable = false)
    private String content;

    /**
     * Indicates whether the message has been read by the receiver.
     * The default value is false (unread message).
     */
    @Column(nullable = false)
    private boolean isRead;

    /**
     * The timestamp when the message was created.
     * It is stored as a TIMESTAMP in the database and defaults to the current date/time.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp = new Date();

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserModel getSender() {
        return sender;
    }

    public void setSender(UserModel sender) {
        this.sender = sender;
    }

    public UserModel getReceiver() {
        return receiver;
    }

    public void setReceiver(UserModel receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Constructor with all fields.
     */
    public MessageModel(Integer id, UserModel sender, UserModel receiver, String content, boolean isRead, Date timestamp) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.isRead = isRead;
        this.timestamp = timestamp;
    }

    /**
     * Default constructor.
     */
    public MessageModel() {
    }

    /**
     * Converts the message object into a readable string format.
     */
    @Override
    public String toString() {
        return "MessageModel{" +
                "id=" + id +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", content='" + content + '\'' +
                ", isRead=" + isRead +
                ", timestamp=" + timestamp +
                '}';
    }
}
