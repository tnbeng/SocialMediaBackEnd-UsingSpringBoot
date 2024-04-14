package in.tnb.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import in.tnb.main.models.Post;
import in.tnb.main.models.User;
import in.tnb.main.response.ApiResponse;
import in.tnb.main.services.PostService;
import in.tnb.main.services.UserService;

@RestController
public class PostController {

	@Autowired
	PostService postService;
	@Autowired
    UserService userService;
	
	@PostMapping("/secure/posts")
	public ResponseEntity<Post> createPost(@RequestBody Post post,@RequestHeader("Authorization") String jwt) throws Exception
	{
		User reg_user=userService.findUserFromToken(jwt);
		Post created_post=postService.createNewPost(post,reg_user.getUserid());
		return new ResponseEntity<>(created_post,HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/deletePost/post/{postid}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable int postid,@RequestHeader("Authorization") String jwt) throws Exception
	{
		User reg_user=userService.findUserFromToken(jwt);
		String message=postService.deletePost(postid, reg_user.getUserid());
		ApiResponse res=new ApiResponse(message,true);
		return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postid}")
	public ResponseEntity<Post> findPostByPostId(@PathVariable int postid) throws Exception
	{
		Post post=postService.findPostByPostId(postid);
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/userPosts")
	public ResponseEntity<List<Post>> findUsersPost(@RequestHeader("Authorization") String jwt)
	{
		User reg_user=userService.findUserFromToken(jwt);
		List<Post> posts=postService.findPostByUserId(reg_user.getUserid());
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
	}
	//This should be for admin only
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> findAllPost() throws Exception
	{
		List<Post> posts=postService.findAllPost();
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
	}
	
	@PutMapping("/createPost/post/{postid}")
	public ResponseEntity<Post> savedPost(@PathVariable int postid,@RequestHeader("Authorization") String jwt) throws Exception
	{
		User reg_user=userService.findUserFromToken(jwt);
		Post post=postService.savedPost(postid,reg_user.getUserid());
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	@PutMapping("/secure/posts/like/{postid}")
	public ResponseEntity<Post> likePost(@PathVariable int postid,@RequestHeader("Authorization") String jwt) throws Exception
	{
		User reg_user=userService.findUserFromToken(jwt);
		
		Post post=postService.likePost(postid,reg_user.getUserid());
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
	
}
