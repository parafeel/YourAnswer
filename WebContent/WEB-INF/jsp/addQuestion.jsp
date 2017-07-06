<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontResource/css/list.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontResource/css/home.css">
<link href="${pageContext.request.contextPath}/frontResource/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/frontResource/bootstrap/js/bootstrap.min.js"></script>

<title>Ask Question --Answer</title>
</head>
<body>

	<%@ include file="/WEB-INF/staticSource/header.jsp"%>
	
	<div class="container">
		<br>
		<br>
		<br>
       	<div class="highlight">
		<form name="addQuestionForm" id="addQuestionForm" method="post" action="addQuestion">
	  		<div class="form-group">
	    		<label for="InputTile">写下你的问题：</label>
	    		<input type="text" class="form-control" name="qTitle" id="qTitle" placeholder="标题" required>
	  		</div>
	  		<div class="form-group">
	    		<label for="InputDetail">问题说明：</label>
	    		<textarea class="form-control" rows="8"  name="qDetail" id="qDetail" placeholder="详情" required></textarea>
	  		</div>
	  		<div class="form-group">
	    		<input type="text" class="form-control" name="qTopic" id="qTopic" placeholder="标签">
	  		</div>
	  		<div>
				<span class="text-left text-danger">${addQuestionMessage}</span>
			</div>
	  		<button type="submit" class="btn btn-default">提交</button>
		</form>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/staticSource/footer.jsp"%>
	
</body>
</html>