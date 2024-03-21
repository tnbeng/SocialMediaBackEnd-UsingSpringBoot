package in.tnb.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.tnb.main.models.Reels;
import in.tnb.main.models.User;
import in.tnb.main.services.ReelsService;
import in.tnb.main.services.UserService;

@RestController
@RequestMapping("/secure")
public class ReelsController {
	@Autowired
	ReelsService reelsService;
	@Autowired
	UserService userService;
	
	@PostMapping("/create/reel")
	public Reels createNewReel(@RequestBody Reels reel,@RequestHeader("Authorization") String jwt) 
	{ 
		User user=userService.findUserFromToken(jwt);
		Reels crated_reel=reelsService.createReel(reel, user);
		return crated_reel;
	}
	@GetMapping("/find/reels")
	public List<Reels> findAllReels() 
	{ 
		return reelsService.findAllReels();
	}
	
	@GetMapping("/find/reels/{userid}")
	public List<Reels> findUserReels(@PathVariable("userid") int userid) throws Exception 
	{ 
		return reelsService.findUserReels(userid);
	}
	

}
