package in.tnb.main.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.tnb.main.models.Chat;
import in.tnb.main.models.Message;
import in.tnb.main.models.User;
import in.tnb.main.repository.ChatRepo;
import in.tnb.main.repository.MessageRepo;

@Service
public class MessageServiceImp implements MessageService{
	
	@Autowired
	MessageRepo messageRepo;

	@Autowired
	ChatService chatService;
	@Autowired
	ChatRepo chatRepo;
	@Override
	public Message createMessage(Message message, int chatid, User user) throws Exception {
		
		Chat chat=chatService.findChatById(chatid);
		
		message.setTimestamp(LocalDateTime.now());
		
		message.setChat(chat);
		message.setUser(user);
		
		chat.getMessages().add(message);
		chatRepo.save(chat);//we have to update the messages in the chat after creating a new message
		
		return messageRepo.save(message);
	}

	@Override
	public List<Message> findChatMessages(int chatid) {
		
		return messageRepo.findByChatId(chatid);
	}

}
