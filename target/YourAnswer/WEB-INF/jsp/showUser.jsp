<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontResource/css/list.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontResource/css/home.css">
<link href="${pageContext.request.contextPath}/frontResource/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/frontResource/bootstrap/js/bootstrap.min.js"></script>

<title>${pointUser.uName} -- Answer</title>
</head>
<body>
	<%@ include file="/WEB-INF/staticSource/header.jsp"%>
	
	<div class="container">
		<br>
		<br>
		<br>
       	<div class="highlight">
       	 	<div class="col-lg-4">
	       	 	<div class="form-group">
		    		<h3>${pointUser.uName }</h3>
		  		</div>
		  		<div class="form-group">
		    		<h6>${pointUser.uWord }</h6> 
		    	</div>
		  		<div class="form-group">
		    		<span class="glyphicon glyphicon-briefcase"> ${pointUser.uResidence } ${pointUser.uProfession }</span>
		    	</div>
	    	
	       		<ul class="nav nav-tabs">
	    			<li class="active"><a href="#Profile" rel="external nofollow" data-toggle="tab" >简介</a></li>
				    <li><a href="#Answer" rel="external nofollow" data-toggle="tab">回答</a></li>
				    <li><a href="#Question" rel="external nofollow" data-toggle="tab">提问</a></li>
			   	</ul>
			   	<div class="tab-content">
				    <div id="Profile" class="active tab-pane">
						<div class="form-group">
				  		</div>
				  		<div class="form-group">
				    		<p>姓名：	${pointUser.uRealName }</p>
				    	</div>
				  		<div class="form-group">
				    		<p>邮箱：	${pointUser.uEmail }</p>
				    	</div>
				    	<div class="form-group">
				    		<p>电话： 	${pointUser.uTel }</p>
				    	</div>
				    	<div class="form-group">
				    		<p>性别：	${pointUser.uGender }</p>
				    	</div>
				    	<div class="form-group">
				    		<p>注册日期：	<fmt:formatDate value="${pointUser.uRegistDate }" pattern="yyyy-MM-dd"/></p>
				    	</div>
					</div>
					
				    <div id="Answer" class="tab-pane">
				    	<div class="highlight">
				    		<c:forEach items="${pointUsersAnswer}" var="answer" varStatus="st">
							    <h4><a href="${pageContext.request.contextPath}/Question/${answer.aBelongToQuestionId}/showAnswer/${answer.aId}" target="_blank">${answer.aBelongToQuestion.qTitle }</a></h4>
							  	<p>	${answer.aContent }</p>
							    <!-- 格式化从数据库读取的时间 -->
							    <p><fmt:formatDate value="${answer.aMadeDate }" pattern="yyyy-MM-dd HH:mm"/></p>
							    <hr>
					   		</c:forEach>
					   	</div>
				    </div>
				    
				    <div id="Question" class="tab-pane">
				    	<div class="highlight">
				    		<c:forEach items="${pointUsersQuestion}" var="question" varStatus="st">
								<h4><a href="${pageContext.request.contextPath}/Question/${question.qId}" target="_blank">${question.qTitle }</a></h4>
							  	<p>	${question.qDetail }</p>
							    <!-- 格式化从数据库读取的时间 -->
							    <p><fmt:formatDate value="${question.qMadeDate }" pattern="yyyy-MM-dd HH:mm"/></p>
							    <hr>
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