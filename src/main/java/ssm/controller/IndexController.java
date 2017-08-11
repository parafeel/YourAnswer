package ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.pojo.Operation;
import ssm.pojo.User;
import ssm.service.OperationService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("")
public class IndexController {

	private OperationService operationService;

	@Autowired
	public void setOperationService(OperationService operationService) {
		this.operationService = operationService;
	}

	//进入首页
	@RequestMapping("")
	public String toHome() {
		return "index";
	}
	@RequestMapping("index")
	public String indexHome() {
		return "index";
	}

	//首页显示的数据接口，返回用户关注的人的动态数据
	@RequestMapping(value = "api/FollowingFeed", method = RequestMethod.GET)
	public @ResponseBody List<Operation> indexFeed(HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");
		List<Operation> operations;
		if(currentUser != null) {
			operations = operationService.getFollowingOperations(currentUser.getuId() , 7);
			return operations;
		} else {
			return null;
		}
	}



}
