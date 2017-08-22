package ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ssm.pojo.User;

//绑定user.xml实现Create、Retrieve(query)、Update、Delete
public interface UserMapper {

//1.User表
	int createUser(User user);
	
	void deleteUserById(Integer id);
	//更新用户信息
	int updateUser(User user);
	//更改用户密码
	int updateUserPassword(User user);
	//更新头像
	int updatePhoto(@Param("uId") Integer uId, @Param("bigPho")String bigPho,
					@Param("midPho")String midPho, @Param("smPho")String smPho);

	//根据uEmail查询加密后的密码
	String queryUserPasswordByuEmail(@Param("uEmail") String uEmail);
	//根据uEmail直接查询用户信息(除密码)
	User queryUserByuEmail(@Param("uEmail") String uEmail);
	//根据uId直接查询用户信息(除密码)
	User queryUserById(@Param("uId") Integer uId);
	//根据用户Id查询用户权限
	int queryUserAuthority(User user);


	List<User> listUser();
	//根据关键字查询相关用户
	List<User> queryUserByKeywords(String keywords);


//2.userRelation表
	int follow(@Param("fromUserId") int fromUserId, @Param("toUserId") int toUserId, @Param("relationType") Byte relationType);

	int updateRelation(@Param("fromUserId") int fromUserId, @Param("toUserId") int toUserId, @Param("relationType") Byte
			relationType);

	int queryRelation(@Param("fromUserId") int fromUserId, @Param("toUserId") int toUserId);

	int queryFollowing(@Param("uId") int uId, @Param("relationType") Byte relationType);

	int queryFollowed(@Param("uId") int uId, @Param("relationType") Byte relationType);

	List<User> queryFollowingUsers(@Param("uId") int uId, @Param("relationType") Byte relationType);

	List<User> queryFollowedUsers(@Param("uId") int uId, @Param("relationType") Byte relationType);


	//queryFeedUserById
	User queryFeedUserById(@Param("uId") Integer uId);
}
