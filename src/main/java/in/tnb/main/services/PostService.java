package in.tnb.main.services;

import java.util.List;

import in.tnb.main.models.Post;

public interface PostService {
   Post createNewPost(Post post,int userid) throws Exception;
  
   String deletePost(int postid,int userid) throws Exception;
   
   Post findPostByPostId(int postid) throws Exception;
   
   List<Post> findAllPost();
   
   List<Post> findPostByUserId(int userid);
   
   Post savedPost(int postid,int userid) throws Exception;
   
   Post likePost(int postid,int userid) throws Exception;
  
  
}
