package com.example.springboot;

import com.example.springboot.DAO.Message;
import com.example.springboot.Requests.CreateMessageRequest;

import java.util.List;
import java.util.UUID;

public interface Controller {

    String createUser(String userName);

    boolean followUser(UUID myId, UUID friendId);

    boolean publishMessage(CreateMessageRequest request);

    List<Message> viewMyTimeline(UUID myId);

    List<Message> viewFriendTimeline(UUID myId, UUID friendId);

}
