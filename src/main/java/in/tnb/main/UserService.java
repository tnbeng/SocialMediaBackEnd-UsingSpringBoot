package in.tnb.main;

import java.util.List;

import in.tnb.main.models.User;

public interface UserService {

    public User findbyId(int id);
    public List<User> findallUser();
    
}
