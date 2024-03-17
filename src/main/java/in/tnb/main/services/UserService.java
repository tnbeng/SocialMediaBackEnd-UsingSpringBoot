package in.tnb.main.services;

import in.tnb.main.models.User;

public interface UserService {
	public User findUserByUserId(int id) throws Exception;
}
