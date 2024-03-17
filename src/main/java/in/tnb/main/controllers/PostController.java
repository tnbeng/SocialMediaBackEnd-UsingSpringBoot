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
import org.springframework.web.bind.annotation.RestController;

import in.tnb.main.models.Post;
import in.tnb.main.response.ApiResponse;
import in.tnb.main.services.PostService;

@RestController
public class PostController {

	@Autowired
	PostService postService;
	
	@PostMapping("/createpost/{userid}")
	public ResponseEntity<Post> createPost(@RequestBody Post post,@PathVariable("userid")int userid) throws Exception
	{
		Post created_post=postService.createNewPost(post,userid);
		return new ResponseEntity<>(created_post,HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/deletepost/post/{postid}/user/{userid}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable int postid,@PathVariable int userid) throws Exception
	{
		String message=postService.deletePost(postid, userid);
		ApiResponse res=new ApiResponse(message,true);
		return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
	}
	
	@GetMapping("/findposts/{postid}")
	public ResponseEntity<Post> findPostByPostId(@PathVariable int postid) throws Exception
	{
		Post post=postService.findPostByPostId(postid);
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/userposts/user/{userid}")
	public ResponseEntity<List<Post>> findUsersPost(@PathVariable int userid)
	{
		List<Post> posts=postService.findPostByUserId(userid);
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
	}
	@GetMapping("/findposts")
	public ResponseEntity<List<Post>> findAllPost() throws Exception
	{
		List<Post> posts=postService.findAllPost();
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
	}
	
	@PutMapping("/savepost/post/{postid}/user/{userid}")
	public ResponseEntity<Post> savedPost(@PathVariable int postid,@PathVariable int userid) throws Exception
	{
		Post post=postService.savedPost(postid, userid);
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	@PutMapping("/likepost/post/{postid}/user/{userid}")
	public ResponseEntity<Post> likePost(@PathVariable int postid,@PathVariable int userid) throws Exception
	{
		Post post=postService.likePost(postid, userid);
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
	
}
