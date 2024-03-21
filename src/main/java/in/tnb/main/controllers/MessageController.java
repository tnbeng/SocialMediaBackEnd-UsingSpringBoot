package in.tnb.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.tnb.main.models.Message;
import in.tnb.main.models.User;
import in.tnb.main.services.MessageService;
import in.tnb.main.services.UserService;

@RestController
@RequestMapping("/secure")
public class MessageController {
 
	@Autowired
	MessageService messageService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/create/message/{chatid}")
	public Message createMessage(@RequestBody Message message, @PathVariable("chatid") int chatid, @RequestHeader("Authorization") String jwt) throws Exception
	{
		User user=userService.findUserFromToken(jwt);
		Message created_message=messageService.createMessage(message, chatid, user);
		return created_message;
	}
	
	@GetMapping("/find/messages/{chatid}")
	public List<Message> findChatMessages(@PathVariable("chatid") int chatid)
	{
		return messageService.findChatMessages(chatid);
	}
}
