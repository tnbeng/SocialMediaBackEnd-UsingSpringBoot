package in.tnb.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.tnb.main.models.Reels;
import in.tnb.main.models.User;
import in.tnb.main.repository.ReelsRepo;

@Service
public class ReelsServiceImp implements ReelsService {

	@Autowired
	ReelsRepo reelsRepo;
	
	@Autowired
	UserService userService;

	@Override
	public Reels createReel(Reels reel, User user) {
		
        reel.setUser(user);
		
		return reelsRepo.save(reel);
	}

	@Override
	public List<Reels> findAllReels() {
		
		return reelsRepo.findAll();
	}

	@Override
	public List<Reels> findUserReels(int userid) throws Exception {
		return reelsRepo.findByUserUserid(userid);
		
	}

}
