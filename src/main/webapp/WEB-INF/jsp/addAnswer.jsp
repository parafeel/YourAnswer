<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 注意静态资源的存放位置 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontResource/css/list.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontResource/css/home.css">
<link href="${pageContext.request.contextPath}/frontResource/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/frontResource/bootstrap/js/bootstrap.min.js"></script>


<script type="text/javascript" src="${pageContext.request.contextPath}/frontResource/JS/wangEditor.min.js"></script>

<title>${currentQuestion.qTitle} --Answer</title>
</head>
<body>
	<%@ include file="/WEB-INF/staticSource/header.jsp"%>

	<div class="container">
	<br>
	<br>
		<div class="highlight" style="background-color: #f6f6f6;">
			<div class="form-group">
				<h3>${currentQuestion.qTitle }</h3>
			</div>
			<hr>
			<div style="padding: 5px 0; color:#999999">问题详情：</div>
			<div class="form-group">
				<div>${currentQuestion.qDetail }</div>
			</div>
			<div class="form-group"  style="color:#999999">
				<!-- 格式化从数据库读取的时间 -->
				<h6>发布于：<fmt:formatDate value="${currentQuestion.qMadeDate }" pattern="yyyy-MM-dd HH:mm"/></h6>
			</div>
		</div>

		<form method="post" action="${pageContext.request.contextPath}/addAnswer/${currentQuestion.qId}">
			<div class="highlight form-group">
				<div style="padding: 5px 0; color:#999999">您的回答：</div>
				<textarea style="display: none" class="form-control" rows="8"  name="aContent" id="aContent"
						  placeholder="您的回答内容" required></textarea>

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
					<input type="hidden" name="qId" id="qId" value="${currentQuestion.qId }">
					<button type="submit" class="btn btn-default" id="addAnswerBtn">添加回答</button>
					<div>
						<span class="text-left text-danger">${addAnswerMessage}</span>
					</div>
				</div>
			</div>
		</form>
	</div>


	<script type="text/javascript" src="${pageContext.request.contextPath}/frontResource/JS/addAnswerAjax.js"></script>
	<%@ include file="/WEB-INF/staticSource/footer.jsp"%>
</body>
</html>