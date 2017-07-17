<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontResource/css/home.css">
<link href="${pageContext.request.contextPath}/frontResource/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js" charset="utf-8" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/frontResource/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>


<style type="text/css">
	html{height: 100%}
	body{margin: 0;height: 100%;
		background: #fff;}
	canvas{display: block;width: 100%;height: 100%;}
	.welcome-text{
		position: absolute;
		top:30%;
		left: 20%;
		height: 20%;
		width: 20%;
		opacity: 0.8;
	}
</style>

	<title>Welcome to Answer</title>
</head>
<body>
	<%@ include file="/WEB-INF/staticSource/header.jsp"%>

	<canvas id="canvas"></canvas>
	<script src="${pageContext.request.contextPath}/frontResource/JS/index.js" type="text/javascript"></script>

	<div class="welcome-text">
		<p>
			你好！${currentUser.uName}
		</p>
	</div>
	
	<%@ include file="/WEB-INF/staticSource/footer.jsp"%>
	
</body>
</html>