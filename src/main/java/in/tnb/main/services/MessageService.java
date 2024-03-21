package in.tnb.main.services;

import java.util.List;

import in.tnb.main.models.Message;
import in.tnb.main.models.User;

public interface MessageService {
	
  public Message createMessage(Message message,int chatid,User user) throws Exception;
  
  public List<Message> findChatMessages(int chatid);
  
}
