package ssm.service;

import ssm.pojo.User;

import java.util.List;


public interface UserService {
	List<User> listUser();
	
	User isUser(String uEmail, String uPassword);
	
	User registUser(User user);
	
	User getUserById(int uId);
	
	User updateUserInfo(User user);
	
}
