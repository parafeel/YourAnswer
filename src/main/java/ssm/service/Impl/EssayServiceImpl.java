package ssm.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ssm.mapper.EssayMapper;
import ssm.pojo.Essay;
import ssm.pojo.User;
import ssm.service.EssayService;

import java.sql.Date;
import java.util.List;

/**
 * Created by wh-pc on 2017/7/25.
 */
@Service
public class EssayServiceImpl implements EssayService{

	private EssayMapper essayMapper;

	@Autowired
	public void setEssayMapper(EssayMapper essayMapper) {
		this.essayMapper = essayMapper;
	}


	@Override
	@Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
	public Essay putEssay(Essay essay, User currentUser) {
		essay.setEssayMadeByUserId(currentUser.getuId());
		Date insertTime= new Date(new java.util.Date().getTime());
		essay.setEssayMadeDate(insertTime);
		int flag = essayMapper.addEssay(essay);
		if(flag == 1) {
			return essay;
		} else {
			return null;
		}
	}

	@Override
	public Essay getEssayByEssayId(int essayId) {
		return essayMapper.queryEssayByEssayId(essayId);
	}

	@Override
	public boolean updateEssay(Essay essay) {
		Date insertTime= new Date(new java.util.Date().getTime());
		essay.setEssayMadeDate(insertTime);
		int flag = essayMapper.updateEssay(essay);
		if(flag == 1) {
			return true;
		} else {
			return  false;
		}
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
	public boolean deleteEssayByEssayId(int essayId) {
		int flag = essayMapper.deleteEssayByEssayId(essayId);
		if(flag == 1) {
			return true;
		} else {
			return  false;
		}
	}

	@Override
	public List<Essay> getEssayByUserId(int essayMadeByUserId) {
		return essayMapper.queryEssayByUserId(essayMadeByUserId);
	}

	@Override
	public Essay getEssayByEssayTitle(String essayTitle) {
		return essayMapper.queryEssayByEssayTitle(essayTitle);
	}

	@Override
	public List<Essay> getEssayByKeyWords(String keywords) {
		return essayMapper.queryEssayByKeyWords(keywords);
	}

	@Override
	public List<Essay> getEssayByTime() {
		return essayMapper.queryEssayByTime();
	}
}
