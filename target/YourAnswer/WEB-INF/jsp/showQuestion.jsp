<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
       	<div class="highlight">
			<div class="form-group">
	    		<h3>${currentQuestion.qTitle }</h3>
	  		</div>
	  		<div class="form-group">
	    		<p>	${currentQuestion.qDetail }</p>
	    	</div>
	  		<div>
	    		<p>问题标签：</p>
	    		<p>	暂无</p>
	    	</div>
	    	
	    	<div class="QuestionButtonGroup" style = "text-align:right;">
	    		<form method="post" action="${pageContext.request.contextPath}/tryAnswer/${currentQuestion.qId}">
	    			<input type="hidden" name="qId" id="qId" value="${currentQuestion.qId }">
	    			<button type="submit" class="btn btn-default">回答</button>
	    		</form>
	    	</div>
		</div>
	</div>
	
	<c:forEach items="${answers}" var="answer" varStatus="st">
        <div class="container">
        	<div class="highlight">
				<div class="form-group" >
					<h6><a href="${pageContext.request.contextPath}/showUser/${answer.aMadeByUserId}" target="_blank">${answer.aMadeByUser.uName }</a> </h6>
		  			<h6 style="color:#999999" >${answer.aMadeByUser.uWord }</h6>
		  			<hr>
		  		</div>
		  		<div class="form-group ">
		    		<p >	${answer.aContent }</p>
		    	</div>
	    		<hr>
	    		<!-- 格式化从数据库读取的时间 -->
	    		<h6 style="color:#999999">编辑于：<fmt:formatDate value="${answer.aMadeDate }" pattern="yyyy-MM-dd HH:mm"/></h6>
		    </div>
		</div>
    </c:forEach>
	
	<%@ include file="/WEB-INF/staticSource/footer.jsp"%>
</body>
</html>