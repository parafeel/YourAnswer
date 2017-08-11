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

<script type="text/javascript" src="${pageContext.request.contextPath}/frontResource/JS/wangEditor.min.js"></script>
<script src="https://cdn.bootcss.com/vue/2.2.2/vue.min.js"></script>

<title>{{question.qTitle}}--Answer</title>
</head>
<body>
	<%@ include file="/WEB-INF/staticSource/header.jsp"%>


	<div class="container">
		<br>
		<br>
		<br>
		<div id="app1">
       	<div class="highlight" style="background-color: #f6f6f6;">
			<div class="form-group">
	    		<h3>{{question.qTitle}}</h3>
				<h6 style="display: none" name="qId" id="qId">${qId}</h6>
	  		</div>
			<hr>
			<div style="padding: 5px 0; color:#999999">问题详情：</div>
			<div class="form-group" v-html="question.qDetail">
			</div>

	  		<div>
				<div style="padding: 5px 0; color:#999999">问题标签：</div>
	    		<p>	暂无</p>
				<div class="form-group"  style="color:#999999">
					<!-- 格式化从数据库读取的时间 -->
					<h6>发布于：{{ new Date(question.qMadeDate).toLocaleString() }}</h6>
				</div>
				<br>
				<div class="QuestionButtonGroup">
					<form method="post" :action=" '${pageContext.request.contextPath}/makeAnswer/' + question.qId">

					</form>
					<a href="#addAnswer"><button type="submit" class="btn btn-default">回答</button></a>
				</div>

	    	</div>
	    	<br>
		</div>

		<template v-for="answer in answerList">
			<div class="container">
				<div class="highlight" style="background-color: #f6f6f6;">
					<div class="form-group" >
						<h6><a :href=" '${pageContext.request.contextPath}/user/' + answer.aMadeByUser.uId"
							   target="_blank">{{answer.aMadeByUser.uName}}</a> </h6>
						<h6 style="color:#999999" >{{answer.aMadeByUser.uWord}}</h6>
						<div class="form-group" v-html="answer.aContent">
						</div>
						<h6 style="color:#999999">编辑于：{{ new Date(answer.aMadeDate).toLocaleString() }}</h6>
						<hr>
						<p v-if="answer.canUpdate">
							<a :href=" '${pageContext.request.contextPath}/answer/' + answer.aId + '/update' "><span
									class="glyphicon glyphicon-pencil"></span> 修改</a>
						</p>
					</div>
				</div>
			</div>
		</template>
		</div>
	</div>

	<br>
	<hr>
	<div id="addAnswer" class="highlight form-group">
		<div style="padding: 5px 0; color:#999999">您的回答：</div>
		<div id="editoraContent">
		</div>
		<script type="text/javascript">
            var E = window.wangEditor;
            var editoraContent = new E('#editoraContent');
            editoraContent.customConfig.zIndex = 100;
            editoraContent.create();
		</script>


		<div class="QuestionButtonGroup" style = "text-align:left;">
			<br>
			<div>
				<div class="text-left alert " role="alert" id="addAnswerMessage"></div>
			</div>
			<button type="submit" class="btn btn-default" id="addAnswerBtn">添加回答</button>
		</div>
	</div>
	<%@ include file="/WEB-INF/staticSource/footer.jsp"%>

	<script type="text/javascript" src="${pageContext.request.contextPath}/frontResource/JS/Question.js"></script>
</body>
</html>