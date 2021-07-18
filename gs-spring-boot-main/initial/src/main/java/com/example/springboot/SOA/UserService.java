package com.example.springboot.SOA;

import com.example.springboot.DAO.Message;
import com.example.springboot.DAO.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    private static UserService instance = null;

    public static UserService getInstance(){
        if(instance==null){
            instance = new UserService();
        }

        return instance;
    }

    private Map<UUID, User> userMap;

    private UserService(){
        this.userMap = new HashMap<>();
    }

    public UUID createUser(String userName){
        if(userName==null || userName.isEmpty() || userName.trim().isEmpty()){
            return null;
        }
        User newUser = new User(userName);
        userMap.put(newUser.getUserID(),newUser);
        return  newUser.getUserID();
    }

    public boolean followUser(UUID myUserId, UUID friendUserId){
        if(!userMap.containsKey(myUserId)){
            logger.error("No user exists with user id: " + myUserId.toString());
            return false;
        }
        User myProfile = userMap.get(myUserId);
        if(!userMap.containsKey(friendUserId)){
            logger.error("No user exists with user id: " + friendUserId.toString());
            return false;
        }
        User friendProfile = userMap.get(friendUserId);
        myProfile.follow(friendProfile);
        return true;
    }

    public boolean publishMessage(UUID userid, String message){
        if(!userMap.containsKey(userid)){
            logger.error("No user exists with user id: " + userid.toString());
            return false;
        }
        if(userMap.containsKey(userid)){
            userMap.get(userid).addMessage(message);
        }
        return true;
    }

    public List<Message> viewFriendTimeline(UUID myUserId, UUID friendUserId){
        if(!userMap.containsKey(myUserId)){
            logger.error("No user exists with user id: " + myUserId.toString());
            return null;
        }
        User myProfile = userMap.get(myUserId);
        if(myProfile.isFollowing(friendUserId)){
            User friendProfile = userMap.get(friendUserId);
            List<Message> friendsMessages = friendProfile.getMyMessages();
            Collections.sort(friendsMessages);
            Collections.reverse(friendsMessages);
            return friendsMessages;
        }
        return null;
    }

    public List<Message> viewMyTimeline(UUID myUserId){
        if(!userMap.containsKey(myUserId)){
            logger.error("No user exists with user id: " + myUserId.toString());
            return null;
        }
        User myProfile = userMap.get(myUserId);
        List<UUID> myFriends = myProfile.getFollowedUsersIds();
        List<Message> myTimeline = new ArrayList<>(myProfile.getMyMessages());

        for(UUID friendId: myFriends){
            User friendProfile = userMap.get(friendId);
            myTimeline.addAll(friendProfile.getMyMessages());
        }

        Collections.sort(myTimeline);
        Collections.reverse(myTimeline);
        return myTimeline;
    }
}
