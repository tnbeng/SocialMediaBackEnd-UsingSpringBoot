package in.tnb.main.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.tnb.main.models.Chat;
import in.tnb.main.models.User;
import in.tnb.main.repository.ChatRepo;

@Service
public class ChatServiceImp implements ChatService{

	@Autowired
	ChatRepo chatRepo;
	
	@Override
	public Chat createChat(User user1, User user2) {
		Chat exist_chat=chatRepo.findChatByUserPair(user1, user2);
		if(exist_chat!=null)
		{
			return exist_chat;
		}
		
		Chat newChat=new Chat();
		//newChat.setChat_name(user2.getFirstname()+" "+user2.getLastname());//i can do like this because in my dashbord his or her name will be displayed with whom i have created chat
		newChat.getUsers().add(user1);
		newChat.getUsers().add(user2);
		newChat.setTimestamp(LocalDateTime.now());
		
		return chatRepo.save(newChat);
			
	}

	@Override
	public Chat findChatById(int chatid) throws Exception {
		Optional<Chat> chat=chatRepo.findById(chatid);
		if(chat.isEmpty())
		{
			throw new Exception("Chat not found with id "+chatid);
		}
		return chat.get();
	}

	@Override
	public List<Chat> findUserChats(int userid) {

	    return chatRepo.findByUsersUserid(userid);
		
	}

}
