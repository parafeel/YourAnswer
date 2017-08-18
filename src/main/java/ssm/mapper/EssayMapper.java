package ssm.mapper;


import org.apache.ibatis.annotations.Param;
import ssm.pojo.Answer;
import ssm.pojo.Essay;
import ssm.pojo.Question;

import java.util.List;

public interface EssayMapper {
	//增
	int addEssay(Essay essay);
	//查
	Essay queryEssayByEssayId(@Param("essayId") Integer essayId);
	//删
	int deleteEssayByEssayId(@Param("essayId") Integer essayId);
	//改
	int updateEssay(Essay essay);


	//根据用户Id查询该用户所有随笔
	List<Essay> queryEssayByUserId(@Param("essayMadeByUserId") Integer essayMadeByUserId);
	//根据title查询随笔
	Essay queryEssayByEssayTitle(@Param("essayTitle") String essayTitle);
	//根据关键字查询相关答案
	List<Essay> queryEssayByKeyWords(@Param("keywords") String keywords);

	List<Essay> queryEssayByTime();

	//queryFeedQuestionById
	Essay queryFeedEssayById(@Param("essayId") Integer essayId);
}
