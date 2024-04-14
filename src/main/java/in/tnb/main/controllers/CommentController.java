package in.tnb.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import in.tnb.main.models.Comment;
import in.tnb.main.models.User;
import in.tnb.main.services.CommentService;
import in.tnb.main.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class CommentController {
    @Autowired
    CommentService commentService;
    
	@Autowired
	UserService userService;
	
	@PostMapping("/secure/commnets/post/{postid}")
	public Comment createComment(@RequestBody Comment comment,@PathVariable("postid") int postid,@RequestHeader("Authorization") String jwt) throws Exception
	{
		User user=userService.findUserFromToken(jwt);
		
		return commentService.createComment(comment, postid,user.getUserid());
		
	}
	
	@PutMapping("/secure/doLike/comment/{commentid}")
	public Comment likeComment(@PathVariable("commentid") int commentid,@RequestHeader("Authorization") String jwt) throws Exception
	{
		User user=userService.findUserFromToken(jwt);
		
		Comment liked_comment=commentService.likeComment(commentid, user.getUserid());
		
		return liked_comment;
	}
	
	@GetMapping("/comments")
	public List<Comment> AllComments()
	{
		return commentService.findAllComments();
	}
	
}
