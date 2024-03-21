package in.tnb.main.services;

import java.util.List;

import in.tnb.main.models.Reels;
import in.tnb.main.models.User;

public interface ReelsService {

	public Reels createReel(Reels reel,User user);
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUserReels(int userid) throws Exception;
}
