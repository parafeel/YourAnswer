package ssm.service.Impl;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import ssm.pojo.Answer;
import ssm.pojo.Essay;
import ssm.pojo.packClass.JsonResult;
import ssm.pojo.Question;
import ssm.pojo.packClass.PackAQE;
import ssm.service.JsonService;

import java.util.List;

/**
 * Created by wh-pc on 2017/8/14.
 */
@Service
public class JsonServiceImpl<T> implements JsonService<T> {

	@Override
	public String toJsonString(T value,int resultCode,String reason) {
		JsonResult<T> jsonResult = new JsonResult<T>();
		jsonResult.setResultCode(resultCode);
		jsonResult.setReason(reason);
		jsonResult.setValue(value);
		return JSON.toJSONString(jsonResult);
	}

	@Override
	public String packToJsonString(int resultCode,String reason,
			List<Question> questions, List<Answer> answers, List<Essay> essays) {
		PackAQE packAQE = new PackAQE();
		packAQE.setResultCode(resultCode);
		packAQE.setReason(reason);
		packAQE.setQuestions(questions);
		packAQE.setAnswers(answers);
		packAQE.setEssays(essays);
		return JSON.toJSONString(packAQE);
	}
}
