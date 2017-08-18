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
		    			<h5>已关注的话题:</h5>
		  			</div>
		       		<ul class="nav nav-pills " role="tablist">
		       		<c:forEach items="${topics}" var="topic" varStatus="st">
						<li role="presentation" class="topicList">
							<a href="#${topic.tName}">${topic.tName} <span class="badge">${topic.tQuestionCount}</span></a>
						</li>
   					</c:forEach>
   					</ul>
   					
   					<div class="tab-content"><!--选项卡的内容-->
   						<div id="${topic.tName}" class="active tab-pane">
   							<div class="form-group">
				    		<p>问题：</p>
				    		<p>回答：</p>
				    		</div>
   						</div>
   					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="jumbotron" align="center">
					<h4 >全部话题</h4>
					<p><a class="btn btn-primary btn-lg" href="#" role="button">进入话题广场</a></p>
				</div>
			</div>
		</div>
	</div>
	
	
	<%@ include file="/WEB-INF/staticSource/footer.jsp"%>
</body>
</html>