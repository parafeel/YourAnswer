<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontResource/css/index.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontResource/css/home.css">
<link href="${pageContext.request.contextPath}/frontResource/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/frontResource/css/amazeui.min.css" rel="stylesheet" type="text/css">

<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/frontResource/bootstrap/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/vue/2.2.2/vue.min.js"></script>
<script src="${pageContext.request.contextPath}/frontResource/JS/other/amazeui.min.js" type="text/javascript" ></script>


<title>Welcome to Answer</title>
</head>
<body>
	<%@ include file="/WEB-INF/staticSource/header.jsp"%>

	<div class="container">
		<div class="row">
			<div class="welcome-text col-md-3 col-md-offset-1">
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
			<div data-am-widget="list_news" class="am-list-news am-list-news-default" >
				<div class="am-list-news-bd">
					<ul class="am-list">
						<template v-for="operation in operations">

							<template v-if="operation.realAction === '回答了问题' || operation.realAction === '关注了问题' ||
				 					operation.realAction === '发表了随笔' ||  operation.realAction === '分享了' ">
							<li class="am-g am-list-item-desced">
								<article class="am-article">
									<div class="am-article-hd">
										<p class="am-article-meta">
											<a :href="'${pageContext.request.contextPath}/user/' +operation.optMadeByUser.uId " target="_blank">
												<img :src=" '${pageContext.request.contextPath}/imgs/userPho/'+ operation.optMadeByUser.uId + '_S.jpg' "
													 class="img-rounded" alt="头像" style="width: 25px ; height: 25px">
												{{operation.optMadeByUser.uName}}
											</a> &nbsp;{{operation.realAction}} &nbsp; - &nbsp;
											{{ new Date(operation.createTime).toLocaleString() }}
										</p>
									</div>
									<div class="am-article-hd">
										<h2>
											<a :href="'${pageContext.request.contextPath}/opt/' + operation.optId ">
												{{operation.optTitle}}
											</a>
										</h2>
									</div>
									<div class="am-list-item-text">
										<div class="am-article-bd">
											<div v-html="onlyShow(operation.optContent)"></div>
										</div>
									</div>
									<div class="am-article-hd">
										<p class="am-article-meta">
											发布于：{{ new Date(operation.optTime).toLocaleString() }}
										</p>
									</div>
								</article>
							</li>
							</template>


							<template v-if="operation.realAction === '赞同了回答' || operation.realAction === '赞了随笔'  ||
									operation.realAction === '赞了分享'">
								<li class="am-g am-list-item-desced">
									<article class="am-article">
										<div class="am-article-hd">
											<p class="am-article-meta">
												<a :href="'${pageContext.request.contextPath}/user/' +operation.optMadeByUser.uId " target="_blank">
													{{operation.optMadeByUser.uName}}
												</a> &nbsp;{{operation.realAction}} &nbsp; - &nbsp;
												{{ new Date(operation.createTime).toLocaleString() }}
											</p>
										</div>
										<div class="am-article-hd">
											<p class="am-article-meta">
												<b><a style="color:#999999" :href="'${pageContext.request.contextPath}/user/' +operation.actionMadeByUser.uId " target="_blank">
													{{operation.actionMadeByUser.uName}}
												</a></b> &nbsp; , &nbsp;
												{{operation.actionMadeByUser.uWord}}
											</p>
											<h2>
												<a :href="'${pageContext.request.contextPath}/opt/' + operation.optId ">
													{{operation.optTitle}}
												</a>
											</h2>
										</div>
										<div class="am-list-item-text">
											<div class="am-article-bd">
												<div v-html="onlyShow(operation.optContent)"></div>
											</div>
										</div>
										<div class="am-article-hd">
											<p class="am-article-meta">
												发布于：{{ new Date(operation.optTime).toLocaleString() }}
											</p>
										</div>
									</article>
								</li>
							</template>

						</template>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/staticSource/footer.jsp"%>

	<script type="text/javascript" src="${pageContext.request.contextPath}/frontResource/JS/index.js"></script>
</body>
</html>