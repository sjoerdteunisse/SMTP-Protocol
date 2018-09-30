package com.AXR.DomainLayer;

import java.util.ArrayList;
import java.util.List;

public class Mail {
    private String hostName;
    private String mailFrom;
    private List<String> recipientTo = new ArrayList<>();
    private StringBuilder body = new StringBuilder();


    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public List<String> getRecipientTo() {
        return recipientTo;
    }

    public void setRecipientTo(String recipientTo) {
        this.recipientTo.add(recipientTo);
    }

    public StringBuilder getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body.append(body);
    }
}
