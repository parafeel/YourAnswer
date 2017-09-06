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
<link href="${pageContext.request.contextPath}/frontResource/css/amazeui.min.css" rel="stylesheet" type="text/css">

<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/frontResource/bootstrap/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/vue/2.2.2/vue.min.js"></script>
<script src="${pageContext.request.contextPath}/frontResource/JS/other/amazeui.min.js" type="text/javascript" ></script>


<title>随笔列表 --Answer</title>
</head>
<body>
	<%@ include file="/WEB-INF/staticSource/header.jsp"%>

	<br>
	<br>
	<br>
	<div class="container">
		<div id="app">
			<div data-am-widget="list_news" class="am-list-news am-list-news-default" >
				<!--列表标题-->
				<div class="am-list-news-hd am-cf">
					<!--带更多链接-->
					<a href="#" class="">
						<h2>随笔列表：</h2>
						<span class="am-list-news-more am-fr">更多 &raquo;</span>
					</a>
				</div>

				<div class="am-list-news-bd">
					<ul class="am-list">
						<template v-for="essay in essays">
							<li class="am-g am-list-item-desced">
								<article class="am-article">
									<div class="am-article-hd">
										<p class="am-article-meta">
											<a style="color:#999999" :href=" '${pageContext.request.contextPath}/user/' + essay.essayMadeByUserId "
											   target="_blank">
												{{essay.essayMadeByUser.uName}}
											</a> &nbsp;发表了随笔：
										</p>
									</div>
									<div class="am-article-hd">
										<h2>
											<a :href=" '${pageContext.request.contextPath}/Essay/' + essay.essayId">
												{{essay.essayTitle}}
											</a>
										</h2>
									</div>
									<div class="am-list-item-text">
										<div class="am-article-bd">
											<div v-html="essay.essayContent"></div>
										</div>
									</div>
									<div class="am-article-hd">
										<p class="am-article-meta">
											发布于：{{ new Date(essay.essayMadeDate).toLocaleString() }}
										</p>
									</div>
								</article>
							</li>
						</template>
					</ul>
				</div>
			</div>
		</div>


		<div data-am-widget="gotop" class="am-gotop am-gotop-fixed" >
			<a href="#top" title="回到顶部">
				<span class="am-gotop-title">回到顶部</span>
				<i class="glyphicon glyphicon-circle-arrow-up"></i>
			</a>
		</div>
	</div>
	<br>
	<br>
	<br>

    <%@ include file="/WEB-INF/staticSource/footer.jsp"%>

	<script type="text/javascript" src="${pageContext.request.contextPath}/frontResource/JS/Essay/Essays.js"></script>
</body>
</html>