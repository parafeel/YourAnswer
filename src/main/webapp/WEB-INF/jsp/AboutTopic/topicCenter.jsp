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


<title>主题广场-- Answer</title>
</head>
<body>
	<%@ include file="/WEB-INF/staticSource/header.jsp"%>
	
	
	<div class="container">
		<br>
		<br>
		<br>

		<div class="row">
			<div class="col-md-8">
		       	<div class="highlight">
		       		<div class="form-group">
		    			<h5>全部话题:</h5>
		  			</div>
					<div  id="app1" v-cloak>
						<template v-for="topic in topics">
							<span class="Tag-content">
								<button type="button" class="am-btn am-btn-default am-round am-btn-xs" @click="showTopicFeed(topic)">
									{{topic.tName}}
								</button>&nbsp;
							</span>
						</template>
						<br>
						<hr data-am-widget="divider" style="" class="am-divider am-divider-default" />
						<a :href=" '${pageContext.request.contextPath}/topic/' + indexTopic.tId">{{indexTopic.tName}}</a>
						<ul id="myTab" class="nav nav-tabs">
							<li class="active"><a href="#question" data-toggle="tab">
								相关问题</a>
							</li>
							<li><a href="#other" data-toggle="tab">其他</a></li>
						</ul>
						<div id="myTabContent" class="tab-content">
							<div class="tab-pane fade in active" id="question">
								<p>11</p>
							</div>
							<div class="tab-pane fade" id="other">
								<p>22</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<h5>热门话题:</h5>
				</div>
			</div>
		</div>

	</div>
	
	
	<%@ include file="/WEB-INF/staticSource/footer.jsp"%>

	<script type="text/javascript" src="${pageContext.request.contextPath}/frontResource/JS/Topic/TopicCenter.js"></script>

</body>
</html>