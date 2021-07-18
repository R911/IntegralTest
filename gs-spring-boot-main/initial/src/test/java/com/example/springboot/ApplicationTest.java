package com.example.springboot;

import com.example.springboot.SOA.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.Assert.*;

@SpringBootTest
public class ApplicationTest {
    private UserService userService = UserService.getInstance();

    @Test
    public void testUserCreation(){
        String name = "Rahul";
        assertNull(userService.createUser(null));
        assertNull(userService.createUser(""));
        assertNull(userService.createUser("  "));
        assertNotNull(userService.createUser(name));
    }

    @Test
    public void testPublishMessage(){
        UUID myId = userService.createUser("Rahul");
        String message = "Nice Weather";
        UUID randomUUID = UUID.randomUUID();

        //Check message created with correct id
        assertTrue(userService.publishMessage(myId,message));

        //Check message not created
        assertFalse(userService.publishMessage(randomUUID,message));

        //Check only one message exists for that user
        assertTrue(userService.viewMyTimeline(myId).size()==1);

        //Check the message is the right message
        assertEquals(userService.viewMyTimeline(myId).get(0).getMessage(),message);

    }

    @Test
    public void testMyTimeline() throws InterruptedException {
        UUID myId = userService.createUser("Rahul");

        String myMessage_1 = "Nice Weather!";
        String myMessage_2 = "Its raining";

        userService.publishMessage(myId,myMessage_1);
        Thread.sleep(2000);
        userService.publishMessage(myId,myMessage_2);

        assertEquals(userService.viewMyTimeline(myId).get(0).getMessage(),myMessage_2);
        assertEquals(userService.viewMyTimeline(myId).get(1).getMessage(),myMessage_1);
        assertNull(userService.viewMyTimeline(UUID.randomUUID()));

    }

    @Test
    public void testFollowUser() throws InterruptedException {
        UUID myId = userService.createUser("Rahul");
        UUID friendId = userService.createUser("Mark");

        String myMessage_1 = "Nice Weather!";
        String myMessage_2 = "Its raining";

        String friendMessage_1 = "Its Muddy outside";
        String friendMessage_2 = "My shoes will get dirty";

        userService.publishMessage(myId,myMessage_1);
        Thread.sleep(2000);
        userService.publishMessage(myId,myMessage_2);


        userService.publishMessage(friendId,friendMessage_1);
        Thread.sleep(2000);
        userService.publishMessage(friendId,friendMessage_2);

        assertFalse(userService.followUser(UUID.randomUUID(),friendId));
        assertFalse(userService.followUser(myId,UUID.randomUUID()));
        assertTrue(userService.followUser(myId,friendId));

        //Message in reverse order
        assertEquals(userService.viewFriendTimeline(myId,friendId).get(0).getMessage(),friendMessage_2);
        assertEquals(userService.viewFriendTimeline(myId,friendId).get(1).getMessage(),friendMessage_1);

        //Not followed reverse
        assertNull(userService.viewFriendTimeline(friendId,myId));

        assertEquals(userService.viewMyTimeline(myId).get(0).getMessage(),friendMessage_2);
        assertEquals(userService.viewMyTimeline(myId).get(1).getMessage(),friendMessage_1);
        assertEquals(userService.viewMyTimeline(myId).get(2).getMessage(),myMessage_2);
        assertEquals(userService.viewMyTimeline(myId).get(3).getMessage(),myMessage_1);
    }



}
