package ssm.service;

import ssm.pojo.User;

import java.util.List;

/**
 * Created by wh-pc on 2017/8/2.
 */
public interface UserRelationService {

	boolean follow(int fromUserId, int toUserId);

	boolean changeRelation(int fromUserId, int toUserId, byte relationType);

	byte getRelationType(int fromUserId, int toUserId);

	int getFollowing(int uId,byte relationType );

	int getFollowed(int uId, byte relationType);

	List<User> getFollowingUsers(int uId, byte relationType);

	List<User> getFollowedUsers(int uId,byte relationType);


}
