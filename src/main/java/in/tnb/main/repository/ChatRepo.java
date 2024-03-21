package in.tnb.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.tnb.main.models.Chat;
import in.tnb.main.models.User;

public interface ChatRepo extends JpaRepository<Chat, Integer> {
   public List<Chat> findByUsersUserid(int userid);
   
   @Query("Select c from Chat c where :user1 Member of c.users And :user2 Member of c.users")
   public Chat findChatByUserPair(@Param("user1") User user1,@Param("user2") User user2);
}
