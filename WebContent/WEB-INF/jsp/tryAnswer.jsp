<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 注意静态资源的存放位置 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontResource/css/list.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontResource/css/home.css">
<link href="${pageContext.request.contextPath}/frontResource/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/frontResource/bootstrap/js/bootstrap.min.js"></script>

<title>Answer ${currentQuestion.qTitle} --Answer</title>
</head>
<body>
	<%@ include file="/WEB-INF/staticSource/header.jsp"%>
	
	<div class="container">
		<br>
		<br>
       	<div class="highlight">
			<div class="form-group">
	    		<h2>${currentQuestion.qTitle }</h2>
	    		<p>	${currentQuestion.qDetail }</p>
	    	</div>
		</div>
		
		<form method="post" action="${pageContext.request.contextPath}/addAnswer/${currentQuestion.qId}">
			<div class="highlight form-group">
	    		<label for="InputDetail">您的回答：</label>
	    		<textarea class="form-control" rows="8"  name="aContent" id="aContent" placeholder="您的回答内容" required></textarea>
	  			<div class="QuestionButtonGroup" style = "text-align:left;">
	  				<br>
	  				<input type="hidden" name="qId" id="qId" value="${currentQuestion.qId }">
	  				<button type="submit" class="btn btn-default">添加回答</button>
	  				<div>
						<span class="text-left text-danger">${addAnswerMessage}</span>
					</div>
	    		</div>
	  		</div>
		</form>
	</div>
	
	
	
	<%@ include file="/WEB-INF/staticSource/footer.jsp"%>
</body>
</html>