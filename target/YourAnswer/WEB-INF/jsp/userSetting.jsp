<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
<script type="text/javascript">
$(function () {
	$("#uploadImg").click(function() {
		$("#file").click();
	});
});
</script>

<title>账户资料-- Answer</title>
</head>
<body>
	<%@ include file="/WEB-INF/staticSource/header.jsp"%>
	
	<div class="container">
		<br>
		<br>
		<br>
		<div class="col-md-12">
       	<div class="highlight">
       		<form name="settingform" id="settingform" method="post" action="${pageContext.request.contextPath}/updateUserSetting">
	       	 	<div class="heading"><h3>用户设置</h3><br></div>
	       	 	<div class="form-group">
	       	 		<div class="form-group">
	       	 			<input type="button" value="选择头像" id="uploadImg" class="btn btn-default"><font color="#999999"><span>&nbsp;&nbsp;支持jpg、jpeg、gif、png、bmp格式的图片</span></font>
	       	 			<input type="file" id="file" name="file"  style="display: none;">
	       	 		</div>
			  		<div class="form-group row">
			    		<div class="col-md-2">
			    			真实姓名 ：
						</div>
						<div class="col-md-6">
							<input type="hidden" class="form-control" value="${settingUser.uId}" name="uId" id="uId">
							<input type="text" class="form-control" value="${settingUser.uRealName}" name="uRealName" id="uRealName" disabled>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-md-2">
							邮箱 ：
						</div>
						<div class="col-md-6">
							<input type="text" class="form-control" value="${settingUser.uEmail }" name="uEmail" id="uEmail" disabled >
						</div>
					</div>
					<div class="form-group row">
						<div class="col-md-2">
							电话：
						</div>
						<div class="col-md-6">
							<input type="text" class="form-control" value="${settingUser.uTel }"  name="uTel" id="uTel" disabled >
						</div>
					</div>
			    	<div class="form-group row">
						<div class="col-md-2">
							用户名 ：
						</div>
						<div class="col-md-6">
							<input type="text" class="form-control" value="${settingUser.uName}" name="uName" id="uName" required>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-md-2">
							一句话介绍 ：
						</div>
						<div class="col-md-6">
							<input type="text" class="form-control" value="${settingUser.uWord }" name="uWord" id="uWord" required>
						</div>
					</div>
						<div class="form-group row">
						<div class="col-md-2">
							性别 ：
						</div>
						<div class="col-md-6">
							<input name="uGender" id="uGender" type="radio" value="男"> 男 <input name="uGender" id="uGender"  type="radio" value="女"> 女
						</div>
					</div>	
					<div class="form-group row">
						<div class="col-md-2">
							居住地 ：
						</div>
						<div class="col-md-6">
							<input type="text" class="form-control" value="${settingUser.uResidence }" name="uResidence" id="uResidence" required>
						</div>
					</div>
			    	<div class="form-group row">
						<div class="col-md-2">
							所在行业 ：
						</div>
						<div class="col-md-6">
							<input type="text" class="form-control" value="${settingUser.uProfession }" name="uProfession" id="uProfession" required>
						</div>
					</div>
					<div>
						<div class="alert " role="alert">${updateUserMessage}</div>
					</div>
				</div>
				<button type="submit" class="btn btn-1">保存</button>
			</form>
		</div>
		</div>
	</div>
	
	
	<%@ include file="/WEB-INF/staticSource/footer.jsp"%>
</body>
</html>