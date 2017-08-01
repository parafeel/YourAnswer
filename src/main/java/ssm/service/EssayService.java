package ssm.service;

import ssm.pojo.Answer;
import ssm.pojo.Essay;
import ssm.pojo.User;

import java.util.List;

public interface EssayService {

	//增加新随笔
	Essay putEssay(Essay essay, User currentUser);
	//查询随笔，通过id
	Essay getEssayByEssayId(int essayId);
	//更新随笔，通过id
	boolean updateEssay(Essay essay);
	//删除随笔，通过id
	boolean deleteEssayByEssayId(int essayId);

	//根据用户Id查询该用户所有随笔
	List<Essay> getEssayByUserId(int essayMadeByUserId);

	//根据Title查询所有随笔
	Essay getEssayByEssayTitle(String essayTitle);

	//根据关键字查询相关随笔
	List<Essay> getEssayByKeyWords(String keywords);

	List<Essay> getEssayByTime();
}
