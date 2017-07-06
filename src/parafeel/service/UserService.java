package parafeel.service;

import java.util.List;

import parafeel.pojo.User;

public interface UserService {
	public List<User> listUser();
	
	public User isUser(String uEmail,String uPassword);
	
	public User registUser(User user);
	
	public User getUserById(int uId);
	
	public User updateUserInfo(User user);
	
}
