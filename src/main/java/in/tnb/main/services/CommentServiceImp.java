package in.tnb.main.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.tnb.main.models.Comment;
import in.tnb.main.models.Post;
import in.tnb.main.models.User;
import in.tnb.main.repository.CommentRepo;
import in.tnb.main.repository.PostRepo;

@Service
public class CommentServiceImp implements CommentService {

	@Autowired
	CommentRepo commentRepo;
	
	@Autowired
	UserService userService;
	
	@Autowired 
	PostService postService;
	
	@Autowired
	PostRepo postRepo;
	
	@Override
	public Comment createComment(Comment comment, int postid, int userid) throws Exception {
		
		User user=userService.findUserByUserId(userid);
		Post post=postService.findPostByPostId(postid);
		
		comment.setUser(user);
		comment.setCreated_time(LocalDateTime.now());
		
		Comment created_comment=commentRepo.save(comment);
		
		post.getComments().add(created_comment);
		postRepo.save(post);
		return created_comment;
	}

	@Override
	public Comment findCommentById(int commentid) throws Exception {
		Optional<Comment> comment=commentRepo.findById(commentid);
		
		if(comment.isEmpty())
		{
			throw new Exception("Comment not found with comment id"+commentid);
		}
		return comment.get();
	}

	@Override
	public Comment likeComment(int commentid, int userid) throws Exception {
		Comment exist_comment=findCommentById(commentid);
		User user=userService.findUserByUserId(userid);
		
		if(exist_comment.getComment_liked_users().contains(user))
		{
			exist_comment.getComment_liked_users().remove(user);
		}
		else 
		{
		   exist_comment.getComment_liked_users().add(user);
		}
		
		Comment liked_comment=commentRepo.save(exist_comment);
		return liked_comment;
	}

	@Override
	public List<Comment> findAllComments() {
		return commentRepo.findAll();
	}

}
