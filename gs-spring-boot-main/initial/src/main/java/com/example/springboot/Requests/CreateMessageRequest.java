package com.example.springboot.Requests;

import java.util.UUID;

public class CreateMessageRequest {
    private UUID userId;
    private String message;

    public CreateMessageRequest(UUID userId, String message){
        this.userId = userId;
        this.message = message;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
