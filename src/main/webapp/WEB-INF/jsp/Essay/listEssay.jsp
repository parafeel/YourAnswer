<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<script src="https://cdn.bootcss.com/vue/2.2.2/vue.min.js"></script>


<title>Try To Answer --Answer</title>
</head>
<body>
	<%@ include file="/WEB-INF/staticSource/header.jsp"%>
	
	<br>
	<br>
	<br>

	<div id="app">
		<template v-for="essay in essays">
			<div class="container">
				<div class="highlight" style="background-color: #f6f6f6;">
					<div class="form-group smallInfo" style="color:#D0D0D0">
						<h6><a style="color:#999999" :href=" '${pageContext.request.contextPath}/user/' + essay.essayMadeByUserId "
							   target="_blank">{{essay.essayMadeByUser.uName}}</a> &nbsp;发表了随笔：</h6>
					</div>
					<hr>
					<div class="form-group">
						<h3>
							<a :href=" '${pageContext.request.contextPath}/Essay/' + essay.essayId">{{essay.essayTitle}}</a>
						</h3>
					</div>
					<div class="form-group" >
						<p>	点击标题查看随笔全部内容</p>
					</div>
					<div class="form-group"  style="color:#999999">
						<!-- 格式化从数据库读取的时间 -->
						<h6>发布于：{{ new Date(essay.essayMadeDate).toLocaleString() }}</h6>
					</div>
				</div>
			</div>
		</template>
	</div>
    
    <div id="myTopBtn" class="text-center">
		<a id="myTopBtn" href="#" class="back-to-top">
			<span class="glyphicon glyphicon-arrow-up">
			</span>
		</a>
	</div>
	<br>
	<br>
	<br>

    <%@ include file="/WEB-INF/staticSource/footer.jsp"%>

	<script type="text/javascript" src="${pageContext.request.contextPath}/frontResource/JS/Essay/Essays.js"></script>
</body>
</html>