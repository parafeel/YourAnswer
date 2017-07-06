package parafeel.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import parafeel.pojo.Answer;

public interface AnswerMapper {
	
	public void addAnswer(Answer answer);
	
	//用@Param("aMadeByUserId")，来标识传入到xml的parameter的名称
	public Answer hasAnswerdById(@Param("aMadeByUserId")Integer aMadeByUserId,@Param("aBelongToQuestionId")Integer aBelongToQuestionId);
	
	public Answer hasAnswerd(Answer answer);
	
	public void deleteAnswerById(Integer aId);
	
	public int updateAnswerById(Answer answer);
	
	public Answer queryAnswerById(Integer aId);
	
	public List<Answer> queryAnswersByQuestion(@Param("aBelongToQuestionId")Integer aBelongToQuestionId);

	public List<Answer> queryAnswersByUserId(@Param("aMadeByUserId")Integer aMadeByUserId);
	
}
