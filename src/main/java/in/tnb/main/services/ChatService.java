package in.tnb.main.services;

import java.util.List;

import in.tnb.main.models.Chat;
import in.tnb.main.models.User;

public interface ChatService {
  public Chat createChat(User user1,User user2);
  
  public Chat findChatById(int chatid) throws Exception;
  
  public List<Chat> findUserChats(int userid);
}
