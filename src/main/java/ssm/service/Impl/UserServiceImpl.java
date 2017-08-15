package ssm.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ssm.mapper.UserMapper;
import ssm.pojo.User;
import ssm.service.UserService;
import ssm.util.Security;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	//实现对User的数据库操作
	private UserMapper userMapper;

	private Security security = Security.getInstance();

	@Override
	public List<User> listUser() {
		// TODO Auto-generated method stub
		return userMapper.listUser();
	}


	@Override
	//登录校验，根据uEmail获取加密后的密码，再判断用户密码是否符合.
	//再加上下面一个方法，合起来返回已登录用户
	public boolean isRightUser(String uEmail,String uPassword) {
		// TODO Auto-generated method stub
		String encodeuPassword = userMapper.queryUserPasswordByuEmail(uEmail);
		if(encodeuPassword != null && security.match(uPassword,encodeuPassword)) {
			return true;
		}
		return false;
	}
	@Override
	public User getUserByuEmail(String uEmail) {
		return userMapper.queryUserByuEmail(uEmail);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackForClassName="Exception")
	//注册用户
	public User registUser(User user) {
		//判断邮箱是否被使用
		User isHasUser = userMapper.queryUserByuEmail(user.getuEmail());
		if(null != isHasUser ) {
			return null;
		}
		//保存原密码，并设置加密密码后往数据库新增用户
		String rawPassword = user.getuPassword();
		user.setuPassword(security.encrypt(rawPassword));
		int isSuccess = userMapper.createUser(user);
		//确定增加成功后，返回注册后的用户
		if(isSuccess == 1) {
			User newUser = userMapper.queryUserByuEmail(user.getuEmail());
			return newUser;
		}
		return null;
	}

	@Override
	//获取用户信息
	public User getUserById(int uId) {
		// TODO Auto-generated method stub
		User user = userMapper.queryUserById(uId);
		return user;
	}

	@Override
	//更新用户信息
	public User updateUserInfo(User user) {
		// TODO Auto-generated method stub
		int isSuccess = userMapper.updateUser(user);
		if(isSuccess == 1) {
			User afterUpadteUser = userMapper.queryUserById(user.getuId());
			return afterUpadteUser;
		}
		return null;
	}

	@Override
	//更新用户密码
	public int updateUserPassword(User user) {
		String rawPassword = user.getuPassword();
		user.setuPassword(security.encrypt(rawPassword));
		int isSuccess = userMapper.updateUserPassword(user);
		return isSuccess;
	}


	@Override
	public int getUserAuthority(User user) {
		int authority = userMapper.queryUserAuthority(user);
		return authority;
	}


	@Override
	public List<User> getUsersByKeyWords(String keywords) {
		return userMapper.queryUserByKeywords(keywords);
	}



}
