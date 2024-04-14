package in.tnb.main.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    private String content; 
    private LocalDateTime created_time;
    
    @ManyToOne
    private User user;
    
    @ManyToMany //why? Here OneToMany could be apply!
    private List<User> comment_liked_users=new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreated_time() {
		return created_time;
	}

	public void setCreated_time(LocalDateTime created_time) {
		this.created_time = created_time;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getComment_liked_users() {
		return comment_liked_users;
	}

	public void setComment_liked_users(List<User> comment_liked_users) {
		this.comment_liked_users = comment_liked_users;
	}
    
    
    
    
    
}
