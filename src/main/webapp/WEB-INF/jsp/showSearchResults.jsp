<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontResource/css/list.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontResource/css/home.css">
<link href="${pageContext.request.contextPath}/frontResource/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/frontResource/bootstrap/js/bootstrap.min.js"></script>

<title> -- Answer</title>
</head>
<body>
	<%@ include file="/WEB-INF/staticSource/header.jsp"%>
	
	<div class="container">
		<br>
		<br>
		<br>
       	<div class="highlight">
       	 	<div class="highlight">
				<div class="form-group" style="padding: 5px 0; color:#999999">
					查询关键字：
				</div>
				<div class="form-group">
					<h4>${keywords}</h4>
				</div>
				<div class="form-group" style="padding: 5px 0; color:#999999">
					查询结果：
				</div>
	    	
	       		<ul class="nav nav-tabs">
	    			<li class="active"><a href="#Question" rel="external nofollow" data-toggle="tab" >相关问题</a></li>
				    <li><a href="#Answer" rel="external nofollow" data-toggle="tab">相关回答</a></li>
				    <li><a href="#User" rel="external nofollow" data-toggle="tab">相关用户</a></li>
			   	</ul>
			   	<div class="tab-content"><!--选项卡的内容-->
				    <div id="Question" class="active tab-pane">
						<div class="highlight" style="background-color: #f6f6f6;">
				    		<c:forEach items="${relatedQuestions}" var="question" varStatus="st">
							    <h4><a href="${pageContext.request.contextPath}/Question/${question.qId} " target="_blank">
							    	${question.qTitle }</a>
							    </h4>
							  	<p>	${question.qDetail} </p>
							    <!-- 格式化从数据库读取的时间 -->
							    <p><fmt:formatDate value="${question.qMadeDate }" pattern="yyyy-MM-dd HH:mm"/></p>
							    <hr>
					   		</c:forEach>
						</div>
				    </div>
				    
				    <div id="Answer" class="tab-pane">
				    	<div class="highlight"  style="background-color: #f6f6f6;">
				    		<c:forEach items="${relatedAnswers}" var="answer" varStatus="st">
								<h4><a href="${pageContext.request.contextPath}/Question
								/${answer.aBelongToQuestionId}/showAnswer/${answer.aId}"
								target="_blank">${answer.aBelongToQuestion.qTitle}</a></h4>
							    <!-- 格式化从数据库读取的时间 -->
								<p>111${answer.aContent}</p>
							    <p><fmt:formatDate value="${answer.aMadeDate }" pattern="yyyy-MM-dd HH:mm"/></p>
							    <hr>
					   		</c:forEach>
					   	</div>
				    </div>

					<div id="User" class="tab-pane">
						<div class="highlight" style="background-color: #f6f6f6;">
				    		<c:forEach items="${relatedUsers}" var="user" varStatus="st">
								<h4><a href="${pageContext.request.contextPath}/user/${user.uId}"
									   target="_blank">${user.uName }</a></h4>
								<div class="form-group">
									<p>真实姓名：${user.uRealName }</p>
									<p>邮箱：${user.uEmail }</p>
								</div>
							</c:forEach>
						</div>
					</div>
			   	
			   	</div>
			</div>
		</div>
	</div>
	
	
	<%@ include file="/WEB-INF/staticSource/footer.jsp"%>
</body>
</html>