package com.ChatApplication.ChatApplication.Projection;

import org.springframework.beans.factory.annotation.Value;

public interface MessageProjection {

    @Value("#{target.sender.email}")
    String getSenderEmail();

    @Value("#{target.receiver.email}")
    String getReceiverEmail();

    String getTimestamp();

    String getContent();
}
