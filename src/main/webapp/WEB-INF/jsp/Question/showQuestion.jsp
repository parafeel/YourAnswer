<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="${pageContext.request.contextPath}/frontResource/css/list.css" rel="stylesheet" type="text/css" >
<link href="${pageContext.request.contextPath}/frontResource/css/home.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/frontResource/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/frontResource/css/amazeui.min.css" rel="stylesheet" type="text/css">

<script src="${pageContext.request.contextPath}/frontResource/JS/other/jquery-3.2.1.min.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/frontResource/bootstrap/js/bootstrap.min.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/frontResource/JS/other/wangEditor.min.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/frontResource/JS/other/vue.min.js"></script>
<script src="${pageContext.request.contextPath}/frontResource/JS/other/amazeui.min.js" type="text/javascript" ></script>

<title>--Answer</title>
</head>
<body>
	<%@ include file="/WEB-INF/staticSource/header.jsp"%>


	<div class="container">
		<br>
		<br>
		<br>
		<div  id="app1" v-cloak>
			<section class="am-panel am-panel-default">
				<header class="am-panel-hd">
					<template v-for="topic in question.qTopics">
						<span class="Tag-content">
							<button type="button" class="am-btn am-btn-secondary am-round am-btn-xs">
								{{topic.tName}}
							</button>
							&nbsp;
						</span>
					</template>
					<hr>
					<div >
						<h2 class="am-panel-title">{{question.qTitle}}</h2>
						<h2 style="display: none" name="qId" id="qId">${qId}</h2>
					</div>
				</header>
				<div class="am-panel-bd">
					<div style="padding: 5px 0; color:#999999">问题详情：</div>
					<div v-html="question.qDetail">
					</div>
					<div style="padding: 5px 0; color:#999999">
						<!-- 格式化从数据库读取的时间 -->
						<h6>发布于：{{ new Date(question.qMadeDate).toLocaleString() }}</h6>
					</div>
					<div class="QuestionButtonGroup">
						<a href="#addAnswer"><button type="button" class="am-btn am-btn-secondary">去回答问题</button></a>
					</div>
				</div>
			</section>

			<hr class="am-article-divider">

			<template v-for="answer in answerList">
				<section class="am-panel am-panel-default">
					<div class="am-panel-bd">
						<a :href=" '${pageContext.request.contextPath}/user/' + answer.aMadeByUser.uId"
						   target="_blank">
							<img :src=" '${pageContext.request.contextPath}/imgs/userPho/'+ answer.aMadeByUser.uId + '_S.jpg' "
								 class="img-rounded" alt="头像" style="width: 25px ; height: 25px">
							{{answer.aMadeByUser.uName}}
							<h6 style="color:#999999;display:inline-block;" >{{answer.aMadeByUser.uWord}}</h6>
						</a>
						<div v-html="answer.aContent">
						</div>
						<div style="color:#999999">
							<!-- 格式化从数据库读取的时间 -->
							<h6>编辑于：{{ new Date(answer.aMadeDate).toLocaleString() }}</h6>
							<p v-if="answer.canUpdate">
								<a :href=" '${pageContext.request.contextPath}/answer/' + answer.aId + '/update' "><span
										class="glyphicon glyphicon-pencil"></span> 修改</a>
							</p>
						</div>
					</div>
				</section>
			</template>
		</div>

		<br>
		<hr>
		<br>
		<article class="am-comment">
			<div id="addAnswer">
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
					<div class="text-left alert " role="alert" id="addAnswerMessage"></div>
					<button type="button" class="am-btn am-btn-secondary" id="addAnswerBtn">添加回答</button>
				</div>
			</div>
		</article>
	</div>
	<%@ include file="/WEB-INF/staticSource/footer.jsp"%>

	<script type="text/javascript" src="${pageContext.request.contextPath}/frontResource/JS/Question/Question.js"></script>
</body>
</html>