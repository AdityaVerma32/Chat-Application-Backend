package com.ChatApplication.ChatApplication.Projection;

import org.springframework.beans.factory.annotation.Value;

/**
 * MessageProjection is an interface-based projection in Spring Data JPA.
 * It is used to retrieve specific fields from the MessageModel entity
 * without loading the entire entity into memory.
 *
 * This projection is useful for optimizing database queries,
 * improving performance, and reducing unnecessary data transfer.
 */
public interface MessageProjection {

    /**
     * Retrieves the email of the sender from the sender UserModel object.
     * Uses SpEL (Spring Expression Language) to fetch the nested email field.
     *
     * @return sender's email as a String
     */
    @Value("#{target.sender.email}")
    String getSenderEmail();

    /**
     * Retrieves the email of the receiver from the receiver UserModel object.
     * Uses SpEL to fetch the nested email field.
     *
     * @return receiver's email as a String
     */
    @Value("#{target.receiver.email}")
    String getReceiverEmail();

    /**
     * Retrieves the timestamp when the message was sent.
     * The timestamp format will depend on the database and serialization settings.
     *
     * @return timestamp as a String (consider changing to Date if needed)
     */
    String getTimestamp();

    /**
     * Retrieves the content of the message.
     *
     * @return message content as a String
     */
    String getContent();
}
