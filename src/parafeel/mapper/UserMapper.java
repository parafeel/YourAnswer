package parafeel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import parafeel.pojo.User;

public interface UserMapper {
	
	public void addUser(User user);
	
	public User hasUEmail(String uEmail);
	
	public void deleteUserById(Integer id);
	
	public int updateUser(User user);
	
	public int updateUserPassword(User user);
	
	public User isUser(@Param("uEmail")String uEmail,@Param("uPassword")String uPassword);
	
	public User queryUserById(@Param("uId")Integer uId);
	
	public List<User> listUser();
	
}
