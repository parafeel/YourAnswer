<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontResource/css/home.css">
<link href="${pageContext.request.contextPath}/frontResource/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js" charset="utf-8" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/frontResource/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontResource/css/index.css">


<title>Welcome to Answer</title>
</head>
<body>
	<%@ include file="/WEB-INF/staticSource/header.jsp"%>
	<div class="row">
		<div class="welcome-text col-md-9">
			<div class="alert alert-info">
				<ul class="nav nav-pills">
					<li role="presentation"><a href="${pageContext.request.contextPath}/makeQuestion">提问</a></li>
					<li role="presentation"><a href="#">回答</a></li>
					<li role="presentation"><a href="${pageContext.request.contextPath}/makeEssay">写随笔</a></li>
				</ul>
			</div>
			<hr>
		</div>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>

	</div>
	<div id="list">
	</div>


	<%@ include file="/WEB-INF/staticSource/footer.jsp"%>

	<script type="text/javascript" src="${pageContext.request.contextPath}/frontResource/JS/index.js"></script>
</body>
</html>