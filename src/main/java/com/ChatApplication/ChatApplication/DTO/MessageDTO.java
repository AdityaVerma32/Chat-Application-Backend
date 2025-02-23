package com.ChatApplication.ChatApplication.DTO;

import javax.xml.crypto.Data;
import java.util.Date;

public class MessageDTO {

    private String senderEmail;
    private String Content;
    private String receiverEmail;
    private Date timestamp;

    public MessageDTO(String senderEmail, String content, String receiverEmail, Date timestamp) {
        this.senderEmail = senderEmail;
        Content = content;
        this.receiverEmail = receiverEmail;
        this.timestamp = timestamp;
    }

    public MessageDTO() {
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "senderEmail='" + senderEmail + '\'' +
                ", Content='" + Content + '\'' +
                ", receiverEmail='" + receiverEmail + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
