<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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


<title>主题-- Answer</title>
</head>
<body>
	<%@ include file="/WEB-INF/staticSource/header.jsp"%>
	
	
	<div class="container">
		<br>
		<br>
		<br>
		<div  id="app1" v-cloak>
			<div class="row">
				<div class="col-md-8">
					<h1>{{topic.tName}}</h1>
					<h2 style="display: none" name="tId" id="tId">${tId}</h2>
					<hr>
				</div>
				<div class="col-md-4">
					<div>
						<button id="followBtn" class="am-btn am-btn-secondary" type="button" @click="followTopic()">关注话题</button>
						<span class="text-left text-danger" id="followTopicMsg"></span>
						<hr>
					</div>
					<div>
						<div>
							{{topic.tDetail}}
						</div>
					</div>
				</div>
			</div>
		</div>

		<div  id="app2" v-cloak>
		<div class="row">
			<div class="col-md-8">
				<div class="am-list-news-bd">
					<ul class="am-list">
						<template v-for="question in questions">
							<li class="am-g am-list-item-desced">
								<article class="am-article">
									<div class="am-article-hd">
										<p class="am-article-meta">
											<a style="color:#999999" :href="'${pageContext.request.contextPath}/user/'+question.qMadeByUserId "
											   target="_blank">{{question.qMadeByUser.uName}}</a>
											&nbsp;提出了问题：
										</p>
									</div>
									<div class="am-article-hd">
										<h2>
											<a :href="'${pageContext.request.contextPath}/Question/' + question.qId ">
												{{question.qTitle}}
											</a>
										</h2>
									</div>
									<div class="am-list-item-text">
										<div class="am-article-bd">
											<div v-html="question.qDetail"></div>
										</div>
									</div>
									<div class="am-article-hd">
										<p class="am-article-meta">
											发布于：{{ new Date(question.qMadeDate).toLocaleString() }}
										</p>
									</div>
								</article>
							</li>
						</template>
					</ul>
				</div>
			</div>
		</div>
		</div>
	</div>
	
	
	<%@ include file="/WEB-INF/staticSource/footer.jsp"%>

	<script type="text/javascript" src="${pageContext.request.contextPath}/frontResource/JS/Topic/ShowTopic.js"></script>

</body>
</html>