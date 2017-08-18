package ssm.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.mapper.UserMapper;
import ssm.pojo.User;
import ssm.service.UserRelationService;
import ssm.util.UserRelation;

import java.util.List;

/**
 * Created by wh-pc on 2017/8/2.
 */

@Service
public class UserRelationServiceImpl implements UserRelationService {

	@Autowired
	private UserMapper userMapper;



	@Override
	public boolean follow(int fromUserId, int toUserId) {
		int flag = userMapper.follow(fromUserId,toUserId, UserRelation.RELATION_FOLLOW);
		if(flag == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean changeRelation(int fromUserId, int toUserId, byte relationType) {
		int flag = userMapper.updateRelation(fromUserId,toUserId, relationType);
		if(flag == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public byte getRelationType(int fromUserId, int toUserId) {
		byte userRelationType = (byte) userMapper.queryRelation(fromUserId, toUserId);
		return userRelationType;
	}

	@Override
	//获取uId关注的用户数量
	public int getFollowing(int uId, byte relationType) {
		int followingCount = userMapper.queryFollowing(uId,relationType);
		return followingCount;
	}

	@Override
	//获取关注uId的用户数量
	public int getFollowed(int uId, byte relationType) {
		int followedCount = userMapper.queryFollowed(uId,relationType);
		return followedCount;
	}

	@Override
	public List<User> getFollowingUsers(int uId, byte relationType) {
		List<User> users = userMapper.queryFollowingUsers(uId,relationType);
		return users;
	}

	@Override
	public List<User> getFollowedUsers(int uId, byte relationType) {
		List<User> users = userMapper.queryFollowedUsers(uId,relationType);
		return users;
	}
}
