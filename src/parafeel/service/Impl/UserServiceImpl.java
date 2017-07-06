package parafeel.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import parafeel.mapper.UserMapper;
import parafeel.pojo.User;
import parafeel.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper userMapper;
	
	@Override
	public List<User> listUser() {
		// TODO Auto-generated method stub
		return userMapper.listUser();
	}

	@Override
	public User isUser(String uEmail,String uPassword) {
		// TODO Auto-generated method stub
		return userMapper.isUser(uEmail,uPassword);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackForClassName="Exception")
	public User registUser(User user) {
		User isHasUser = userMapper.hasUEmail(user.getuEmail());
		if(isHasUser != null)
			return null;
		userMapper.addUser(user);
		User newUser = userMapper.isUser(user.getuEmail(),user.getuPassword());
		if( newUser != null) {
			return newUser;
		} else {
			return null;
		}
	}

	@Override
	public User getUserById(int uId) {
		// TODO Auto-generated method stub
		User user = userMapper.queryUserById(uId);
		return user;
	}

	@Override
	public User updateUserInfo(User user) {
		// TODO Auto-generated method stub
		userMapper.updateUser(user);
		User afterUpadteUser = userMapper.queryUserById(user.getuId());
		return afterUpadteUser;
	}
	
	
	

}
