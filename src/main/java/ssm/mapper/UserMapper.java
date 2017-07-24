package ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ssm.pojo.User;

//绑定user.xml实现Create、Retrieve(query)、Update、Delete
public interface UserMapper {

	
	int createUser(User user);
	
	void deleteUserById(Integer id);
	//更新用户信息
	int updateUser(User user);
	//更改用户密码
	int updateUserPassword(User user);

	//根据uEmail查询加密后的密码
	String queryUserPasswordByuEmail(@Param("uEmail") String uEmail);
	//根据uEmail直接查询用户信息(除密码)
	User queryUserByuEmail(@Param("uEmail") String uEmail);
	//根据uId直接查询用户信息(除密码)
	User queryUserById(@Param("uId") Integer uId);


	List<User> listUser();
	//根据关键字查询相关用户
	List<User> queryUserByKeywords(String keywords);
}
