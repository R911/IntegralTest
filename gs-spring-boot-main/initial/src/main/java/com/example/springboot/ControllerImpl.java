package com.example.springboot;

import com.example.springboot.DAO.Message;
import com.example.springboot.Requests.CreateMessageRequest;
import com.example.springboot.SOA.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/network/")
public class ControllerImpl implements Controller {

	private UserService userService;

	ControllerImpl(){
		this.userService = UserService.getInstance();
	}

	@GetMapping("/create/user/")
	public String createUser(@RequestParam() String userName){
		UUID userId = userService.createUser(userName);
		return userId.toString();
	}

	@GetMapping("/follow/")
	public boolean followUser(@RequestParam() UUID myId, @RequestParam() UUID friendId){
		return userService.followUser(myId,friendId);
	}

	@PostMapping("/publish/message/")
	public boolean publishMessage(@RequestBody CreateMessageRequest messageRequest){
		return userService.publishMessage(messageRequest.getUserId(),messageRequest.getMessage());
	}

	@GetMapping("/view/mytimeline")
	public List<Message> viewMyTimeline(@RequestParam() UUID myId){
		return userService.viewMyTimeline(myId);
	}

	@GetMapping("/view/friendTimeline")
	public List<Message> viewFriendTimeline(@RequestParam() UUID myId, @RequestParam() UUID friendId){
		return userService.viewFriendTimeline(myId,friendId);
	}

}
