package ssm.service;

import ssm.pojo.Answer;
import ssm.pojo.Essay;
import ssm.pojo.Question;

import java.util.List;

/**
 * Created by wh-pc on 2017/8/14.
 */
public interface JsonService<T> {

	//把各个对象包装成JSON字符串
	String toJsonString(T value,int resultCode,String reason);

	String packToJsonString(int resultCode,String reason,
			List<Question> questions, List<Answer> answers, List<Essay> essays);

}
