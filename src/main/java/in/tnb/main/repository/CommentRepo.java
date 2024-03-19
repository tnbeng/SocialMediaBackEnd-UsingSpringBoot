package in.tnb.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.tnb.main.models.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
