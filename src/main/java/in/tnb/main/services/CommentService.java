package in.tnb.main.services;

import java.util.List;

import in.tnb.main.models.Comment;

public interface CommentService {
 
	public Comment createComment(Comment comment,int postid,int userid) throws Exception;
	
	public Comment findCommentById(int commentid) throws Exception;
	public List<Comment> findAllComments();
	
	public Comment likeComment(int commentid,int userid) throws Exception;
	
}
