<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontResource/css/home.css">
<link href="${pageContext.request.contextPath}/frontResource/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/frontResource/JS/other/jquery-3.2.1.min.js" charset="utf-8"
		type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/frontResource/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/frontResource/JS/other/vue.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontResource/css/index.css">


<title>Welcome to Answer</title>
</head>
<body>
	<%@ include file="/WEB-INF/staticSource/header.jsp"%>
	<div class="container">
		<div class="row">
			<div class="welcome-text col-md-9">
				<div class="alert alert-info">
					<ul class="nav nav-pills">
						<li role="presentation"><a href="${pageContext.request.contextPath}/makeQuestion">提问</a></li>
						<li role="presentation"><a href="#">回答</a></li>
						<li role="presentation"><a href="${pageContext.request.contextPath}/makeEssay">写随笔</a></li>
					</ul>
				</div>
			</div>
			<hr>
		</div>

		<div id="app">
			<template v-for="operation in operations">
				<template v-if="operation.realAction === '回答了问题' || operation.realAction === '关注了问题' ||
				 					operation.realAction === '发表了随笔' ||  operation.realAction === '分享了' ">
					<div class="container row">
						<div class="container col-md-9">
							<div class="smallInfo">
								<hr>
								<h6><a :href="'${pageContext.request.contextPath}/user/' +
								   operation.optMadeByUser.uId " target="_blank">
									{{operation.optMadeByUser.uName}}</a> &nbsp;{{operation.realAction}} &nbsp; - &nbsp;
									{{ new Date(operation.createTime).toLocaleString() }}
								</h6>
							</div>
							<div class="highlight" style="background-color: #f6f6f6;">
								<div class="form-group">
									<h3><a :href="'${pageContext.request.contextPath}/opt/' + operation.optId ">
										{{operation.optTitle}}</a></h3>
								</div>
								<div>
									<div class="form-group"  v-html="onlyShow(operation.optContent)">
								</div>
								</div>
								<div class="form-group"  style="color:#999999">
									<!-- 格式化从数据库读取的时间 -->
									<h6>发布于：{{ new Date(operation.optTime).toLocaleString() }}</h6>
								</div>
							</div>
							<br>
						</div>
					</div>
				</template>

				<template v-if="operation.realAction === '赞同了回答' || operation.realAction === '赞了随笔'  ||
								 	operation.realAction === '赞了分享'">
					<div class="container row">
						<div class="container col-md-9">
							<div class="smallInfo">
								<hr>
								<h6><a :href="'${pageContext.request.contextPath}/user/' +
								   operation.optMadeByUser.uId " target="_blank">
									{{operation.optMadeByUser.uName}}</a> &nbsp;{{operation.realAction}} &nbsp; - &nbsp;
									{{ new Date(operation.createTime).toLocaleString() }}
								</h6>
							</div>
							<div class="highlight" style="background-color: #f6f6f6;">
								<div class="form-group smallInfo" style="color:#D0D0D0">
									<h6><b><a style="color:#999999" :href="'${pageContext.request.contextPath}/user/' +
								   operation.actionMadeByUser.uId " target="_blank">
										{{operation.actionMadeByUser.uName}}</a></b> &nbsp; , &nbsp;
										{{operation.actionMadeByUser.uWord}}
									</h6>
								</div>
								<hr>
								<div class="form-group">
									<h3><a :href="'${pageContext.request.contextPath}/opt/' + operation.optId ">
										{{operation.optTitle}}</a></h3>
								</div>
								<div class="form-group" v-html="onlyShow(operation.optContent)">
								</div>
								<div class="form-group"  style="color:#999999">
									<!-- 格式化从数据库读取的时间 -->
									<h6>发布于：{{ new Date(operation.optTime).toLocaleString() }}</h6>
								</div>
							</div>
							<br>
						</div>
					</div>
				</template>
			</template>
		</div>


	</div>

	<%@ include file="/WEB-INF/staticSource/footer.jsp"%>

	<script type="text/javascript" src="${pageContext.request.contextPath}/frontResource/JS/index.js"></script>
</body>
</html>