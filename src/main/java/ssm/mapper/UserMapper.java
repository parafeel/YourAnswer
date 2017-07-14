package ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ssm.pojo.User;

public interface UserMapper {
	
	void addUser(User user);
	
	User hasUEmail(String uEmail);
	
	void deleteUserById(Integer id);
	
	int updateUser(User user);
	
	int updateUserPassword(User user);
	
	User isUser(@Param("uEmail") String uEmail, @Param("uPassword") String uPassword);
	
	User queryUserById(@Param("uId") Integer uId);
	
	List<User> listUser();
	
}
