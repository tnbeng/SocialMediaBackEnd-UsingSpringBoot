package in.tnb.main.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.tnb.main.models.Post;
import in.tnb.main.models.User;
import in.tnb.main.repository.PostRepo;
import in.tnb.main.repository.UserRepo;

@Service
public class PostServiceImp implements PostService {

	@Autowired
	PostRepo postRepo;
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepo userRepo;
	@Override
	public Post createNewPost(Post post, int userid) throws Exception {
		User user=userService.findUserByUserId(userid);
		Post newPost=new Post();
		newPost.setCaption(post.getCaption());
		newPost.setImage_name(post.getImage_name());
		newPost.setVideo_name(post.getVideo_name());
		newPost.setCreatetime(LocalDateTime.now());
	    newPost.setUser(user);
		
		
		return postRepo.save(newPost);
	}

	@Override
	public String deletePost(int postid, int userid) throws Exception {
		Post exist_post=findPostByPostId(postid);
		User exist_user=userService.findUserByUserId(userid);
		
		if(exist_post.getUser().getUserid()!=exist_user.getUserid())
		{
			throw new Exception("You can not delete another user's post");
		}
		
		postRepo.deleteById(postid);
		
	    return "Post deleted successfully with post id " +postid;
	}

	@Override
	public Post findPostByPostId(int postid) throws Exception {
		Optional<Post> post=postRepo.findById(postid);
		System.out.println(post.get());
		if(post.isEmpty())
		{
			throw new Exception("Post not found with post id"+postid);
		}
		return post.get();
	}

	@Override
	public List<Post> findAllPost() {
		return postRepo.findAll();
	}

	@Override
	public List<Post> findPostByUserId(int userid) {
		return postRepo.findPostByUserId(userid);
	}

	@Override //save Post to user field
	public Post savedPost(int postid, int userid) throws Exception {
		Post exist_post=findPostByPostId(postid);
		User exist_user=userService.findUserByUserId(userid);
		
		if(exist_user.getSavedPost().contains(exist_post))
		{
			exist_user.getSavedPost().remove(exist_post);
		}
		else
		{
			exist_user.getSavedPost().add(exist_post);
		}
		userRepo.save(exist_user);
		return exist_post;
	}

	@Override
	public Post likePost(int postid, int userid) throws Exception {
		Post exist_post=findPostByPostId(postid);
		User exist_user=userService.findUserByUserId(userid);
		
		if(exist_post.getLiked_users().contains(exist_user))
		{
			exist_post.getLiked_users().remove(exist_user);
		}
		else
		{
			exist_post.getLiked_users().add(exist_user);
		}
		
		return postRepo.save(exist_post);
	}

}
