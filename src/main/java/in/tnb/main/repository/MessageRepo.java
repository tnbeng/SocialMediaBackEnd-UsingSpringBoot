package in.tnb.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.tnb.main.models.Message;

public interface MessageRepo extends JpaRepository<Message, Integer>{
  public List<Message> findByChatId(int chatid);
}
