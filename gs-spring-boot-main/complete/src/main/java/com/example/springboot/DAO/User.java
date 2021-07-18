package com.example.springboot.DAO;

import java.util.*;

public class User {

    private UUID userID;
    private String userName;
    private Set<UUID> followedUserIds;
    private Deque<Message> myMessages;

    public User(String userName){
        this.userID = UUID.randomUUID();
        this.userName = userName;
        this.followedUserIds = new HashSet<>();
        this.myMessages = new LinkedList<>();
    }

    //Notification of following not added
    public boolean follow(User user){
        if(!followedUserIds.contains(user.userID)){
            followedUserIds.add(user.userID);
            return true;
        }
        return false;
    }

    public boolean isFollowing(UUID friendId){
        return this.followedUserIds.contains(friendId);
    }

    public boolean unfollow(User user){
        if(followedUserIds.contains(user.userID)){
            followedUserIds.remove(user.userID);
            return true;
        }
        return false;
    }

    //Notification of message not added right now
    public void addMessage(String value){
        Message message = new Message(this.userID,this.userName,value);
        myMessages.addFirst(message);
    }

    public List<Message> getMyMessages(){
        return new ArrayList<>(this.myMessages);
    }

    public UUID getUserID(){
        return this.userID;
    }

    public List<UUID> getFollowedUsersIds(){
        return new ArrayList<>(this.followedUserIds);
    }


}
