package ssm.service;

import ssm.pojo.User;

import java.util.List;


public interface UserService {
	//用户登录
	User isRightUser(String uEmail, String uPassword);
	//注册用户
	User registUser(User user);
	//根据uId获取用户信息
	User getUserById(int uId);
	//更新用户信息
	User updateUserInfo(User user);
	//更新用户密码
	int updateUserPassword(User user);

	List<User> listUser();
	
}
