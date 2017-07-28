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

<title>${currentEssay.essayTitle } --Answer</title>
</head>
<body>
	<%@ include file="/WEB-INF/staticSource/header.jsp"%>
	
	<div class="container">
		<br>
		<br>
		<br>
       	<div class="highlight" style="background-color: #f6f6f6;">
			<div class="form-group">
	    		<h3 align="center">${currentEssay.essayTitle }</h3>
	  		</div>
			<hr>
			<div style="padding: 5px 0; color:#999999">
				<h6><a href="${pageContext.request.contextPath}/user/${currentEssay.essayMadeByUser.uId}"
					   target="_blank">${currentEssay.essayMadeByUser.uName}</a>发布于：</h6>
				<h6><fmt:formatDate value="${currentEssay.essayMadeDate }" pattern="yyyy-MM-dd HH:mm"/></h6>
			</div>
	  		<div class="form-group">
	    		<div>	${currentEssay.essayContent }</div>
	    	</div>
	  		<div>
				<div style="padding: 5px 0; color:#999999">问题标签：</div>
	    		<p>	暂无</p>
				<br>
	    	</div>

		</div>
	</div>
	
	<%@ include file="/WEB-INF/staticSource/footer.jsp"%>
</body>
</html>