package com.ChatApplication.ChatApplication.DTO;

import java.util.Date;

/**
 * Data Transfer Object (DTO) for handling chat messages.
 * This class encapsulates message details, including sender, receiver, content, and timestamp.
 */
public class MessageDTO {

    private String senderEmail; // Email of the message sender
    private String content; // The actual message content
    private String receiverEmail; // Email of the message recipient
    private Date timestamp; // Timestamp when the message was sent

    /**
     * Constructor to initialize MessageDTO with values.
     *
     * @param senderEmail   The sender's email.
     * @param content       The message content.
     * @param receiverEmail The recipient's email.
     * @param timestamp     The time the message was sent.
     */
    public MessageDTO(String senderEmail, String content, String receiverEmail, Date timestamp) {
        this.senderEmail = senderEmail;
        this.content = content;
        this.receiverEmail = receiverEmail;
        this.timestamp = timestamp;
    }

    /**
     * Default constructor required for frameworks like Spring to instantiate objects dynamically.
     */
    public MessageDTO() {
    }

    /**
     * Retrieves the sender's email.
     *
     * @return The sender's email address.
     */
    public String getSenderEmail() {
        return senderEmail;
    }

    /**
     * Sets the sender's email.
     *
     * @param senderEmail The sender's email address.
     */
    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    /**
     * Retrieves the message content.
     *
     * @return The message content as a String.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the message content.
     *
     * @param content The message text.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Retrieves the receiver's email.
     *
     * @return The recipient's email address.
     */
    public String getReceiverEmail() {
        return receiverEmail;
    }

    /**
     * Sets the receiver's email.
     *
     * @param receiverEmail The recipient's email address.
     */
    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    /**
     * Retrieves the timestamp of the message.
     *
     * @return The timestamp as a Date object.
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp of the message.
     *
     * @param timestamp The date and time the message was sent.
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Returns a string representation of the MessageDTO object.
     *
     * @return A formatted string containing message details.
     */
    @Override
    public String toString() {
        return "MessageDTO{" +
                "senderEmail='" + senderEmail + '\'' +
                ", content='" + content + '\'' +
                ", receiverEmail='" + receiverEmail + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
