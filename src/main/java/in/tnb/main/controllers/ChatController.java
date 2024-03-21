package in.tnb.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.tnb.main.models.Chat;
import in.tnb.main.models.User;
import in.tnb.main.repository.ChatRepo;
import in.tnb.main.services.ChatService;
import in.tnb.main.services.UserService;

@RestController
@RequestMapping("/secure")
public class ChatController {
	
	@Autowired
	ChatService chatService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/create/chat/{userid}")
	public Chat createChat(@RequestHeader("Authorization")String jwt,@PathVariable("userid") int userid) throws Exception
	{
		User user1=userService.findUserFromToken(jwt);
		User user2=userService.findUserByUserId(userid);
		return chatService.createChat(user1,user2 );
	}
	
	@GetMapping("/find/chats")
	public List<Chat> findUserChats(@RequestHeader("Authorization")String jwt)
	{
		User user=userService.findUserFromToken(jwt);
		return chatService.findUserChats(user.getUserid());
	}

}
