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
       	 	<div class="col-lg-12">
				<div class="form-group">
					<h3>${pointUser.uName }</h3>
				</div>
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<h6>${pointUser.uWord }</h6>
							<h6 style="display: none" id="pointUserId">${pointUser.uId}</h6>
						</div>
						<div class="form-group">
							<span class="glyphicon glyphicon-briefcase"> ${pointUser.uResidence } ${pointUser.uProfession }</span>
						</div>
					</div>
					<div class="col-lg-6">
						<div class="row" align="center">
							<div class="form-group col-lg-2">
								<strong>关注了</strong>
								<h6 id="following">0</h6>
							</div>
							<div class="form-group col-lg-2">
								<strong>关注者</strong>
								<h6 id="followed">0</h6>
							</div>
							<div class="form-group col-lg-2">
								<button id="followBtn" class="btn btn-default" type="button">关注</button>
							</div>
						</div>
					</div>
				</div>
	       		<ul class="nav nav-tabs">
	    			<li class="active"><a href="#Profile" rel="external nofollow" data-toggle="tab" >简介</a></li>
				    <li><a href="#Answer" rel="external nofollow" data-toggle="tab">回答</a></li>
				    <li><a href="#Question" rel="external nofollow" data-toggle="tab">提问</a></li>
				    <li><a href="#Essay" rel="external nofollow" data-toggle="tab">随笔</a></li>
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
								<c:if test="${answer.aMadeByUserId == currentUser.uId}">
									<a href="${pageContext.request.contextPath}/answer/${answer.aId}/update"><span
											class="glyphicon glyphicon-pencil"></span> 修改</a>
								</c:if>
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

					<div id="Essay" class="tab-pane">
						<div class="highlight">
							<c:forEach items="${pointUserEssay}" var="essay" varStatus="st">
								<h4><a href="${pageContext.request.contextPath}/Essay/${essay.essayId}"
									   target="_blank">${essay.essayTitle }</a></h4>
								<p>	点击标题查看随笔全部内容</p>
								<!-- 格式化从数据库读取的时间 -->
								<p><fmt:formatDate value="${essay.essayMadeDate }" pattern="yyyy-MM-dd HH:mm"/></p>
								<c:if test="${essay.essayMadeByUserId == currentUser.uId}">
									<a href="${pageContext.request.contextPath}/Essay/${essay.essayId}/update"><span
											class="glyphicon glyphicon-pencil"></span> 修改</a>
								</c:if>
								<hr>
							</c:forEach>
						</div>
					</div>
			   	
			   	</div>
			</div>
		</div>
	</div>
	
	
	<%@ include file="/WEB-INF/staticSource/footer.jsp"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/frontResource/JS/follow.js">
</script>
</body>
</html>