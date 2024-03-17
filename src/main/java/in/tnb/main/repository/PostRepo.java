package in.tnb.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.tnb.main.models.Post;

public interface PostRepo extends JpaRepository<Post, Integer> {

	@Query("select p from Post p where p.user.userid=:id")
	List<Post> findPostByUserId(int id);
}
