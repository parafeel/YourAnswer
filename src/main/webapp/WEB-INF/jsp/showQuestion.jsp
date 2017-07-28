<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<title>${currentQuestion.qTitle } --Answer</title>
</head>
<body>
	<%@ include file="/WEB-INF/staticSource/header.jsp"%>
	
	<div class="container">
		<br>
		<br>
		<br>
       	<div class="highlight" style="background-color: #f6f6f6;">
			<div class="form-group">
	    		<h3>${currentQuestion.qTitle }</h3>
	  		</div>
			<hr>
			<div style="padding: 5px 0; color:#999999">问题详情：</div>
	  		<div class="form-group">
	    		<div>	${currentQuestion.qDetail }</div>
	    	</div>
	  		<div>
				<div style="padding: 5px 0; color:#999999">问题标签：</div>
	    		<p>	暂无</p>
				<div class="form-group"  style="color:#999999">
					<!-- 格式化从数据库读取的时间 -->
					<h6>发布于：<fmt:formatDate value="${currentQuestion.qMadeDate }" pattern="yyyy-MM-dd HH:mm"/></h6>
				</div>
				<br>
				<div class="QuestionButtonGroup">
					<form method="post" action="${pageContext.request.contextPath}/makeAnswer/${currentQuestion.qId}">
						<input type="hidden" name="qId" id="qId" value="${currentQuestion.qId }">
						<button type="submit" class="btn btn-default">回答</button>
					</form>
				</div>
	    	</div>
	    	<br>

		</div>
	</div>
	
	<c:forEach items="${answers}" var="answer" varStatus="st">
        <div class="container">
        	<div class="highlight" style="background-color: #f6f6f6;">
				<div class="form-group" >
					<h6><a href="${pageContext.request.contextPath}/user/${answer.aMadeByUserId}"
						   target="_blank">${answer.aMadeByUser.uName }</a> </h6>
		  			<h6 style="color:#999999" >${answer.aMadeByUser.uWord }</h6>
		  			<hr>
		  		</div>
		  		<div class="form-group ">
		    		<p >	${answer.aContent }</p>
		    	</div>
	    		<hr>
	    		<!-- 格式化从数据库读取的时间 -->
	    		<h6 style="color:#999999">编辑于：<fmt:formatDate value="${answer.aMadeDate }" pattern="yyyy-MM-dd HH:mm"/></h6>
				<c:if test="${answer.aMadeByUserId == currentUser.uId}">
					<a href="${pageContext.request.contextPath}/answer/${answer.aId}/update"><span
							class="glyphicon glyphicon-pencil"></span> 修改</a>
				</c:if>

		    </div>
		</div>
    </c:forEach>
	
	<%@ include file="/WEB-INF/staticSource/footer.jsp"%>
</body>
</html>