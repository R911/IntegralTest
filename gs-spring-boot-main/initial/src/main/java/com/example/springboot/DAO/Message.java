package com.example.springboot.DAO;

import java.sql.Timestamp;
import java.util.UUID;

public class Message implements Comparable<Message> {

    private UUID messageId;
    private UUID userId;
    private String userName;
    private String value;
    private Timestamp tStamp;

    public Message(UUID userId, String userName ,String message){
        this.messageId = UUID.randomUUID();
        this.userId = userId;
        this.userName = userName;
        this.tStamp = new Timestamp(System.currentTimeMillis());
        this.value = message;
    }

    public String getMessage(){
        return this.value;
    }

    public Timestamp getTimestamp(){
        return this.tStamp;
    }

    public UUID getUserId() {return this.userId;}

    public String getUserName(){ return  this.userName;}

    @Override
    public int compareTo(Message o) {
        if(this.tStamp.after(o.tStamp)){
            return 1;
        }
        else if(this.tStamp.before(o.tStamp)){
            return -1;
        }
        else{
            return 0;
        }
    }
}
