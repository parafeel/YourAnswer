<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
<script type="text/javascript" src="${pageContext.request.contextPath}/frontResource/JS/user/userSetting.js"></script>

<title>账户资料-- Answer</title>
</head>
<body>
	<%@ include file="/WEB-INF/staticSource/header.jsp"%>
	
	<div class="container">
		<br>
		<br>
		<br>
       	<div class="highlight col-md-12 row">

			<ul class="nav nav-tabs">
				<li class="active"><a href="#setting" rel="external nofollow" data-toggle="tab" >用户资料</a></li>
				<li><a href="#changePassword" rel="external nofollow" data-toggle="tab" >更改密码</a></li>
				<li><a href="#change1" rel="external nofollow" data-toggle="tab">change1</a></li>
				<li><a href="#change2" rel="external nofollow" data-toggle="tab">change2</a></li>
			</ul>
			<div class="tab-content"><!--选项卡的内容-->
				<div id="setting" class="active tab-pane ">
					<div class="heading"><h3>用户资料</h3><br></div>
						<div class="form-group">
							<div class="form-group">
								<input type="button" value="选择头像" id="uploadImg" class="btn btn-default"><font color="#999999"><span>&nbsp;&nbsp;支持jpg、jpeg、gif、png、bmp格式的图片</span></font>
								<input type="file" id="file" name="file"  style="display: none;">
							</div>
							<div class="form-group row">
								<div class="col-md-2">
									真实姓名 ：
								</div>
								<div class="col-md-8">
									<input type="hidden" class="form-control" value="${settingUser.uId}" name="uId" id="uId">
									<input type="text" class="form-control" value="${settingUser.uRealName}" name="uRealName" id="uRealName" disabled>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-md-2">
									邮箱 ：
								</div>
								<div class="col-md-8">
									<input type="text" class="form-control" value="${settingUser.uEmail }" name="uEmail" id="uEmail" disabled >
								</div>
							</div>
							<div class="form-group row">
								<div class="col-md-2">
									电话：
								</div>
								<div class="col-md-8">
									<input type="text" class="form-control" value="${settingUser.uTel }"  name="uTel" id="uTel" disabled >
								</div>
							</div>
							<div class="form-group row">
								<div class="col-md-2">
									用户名 ：
								</div>
								<div class="col-md-8">
									<input type="text" class="form-control" value="${settingUser.uName}" name="uName" id="uName" required>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-md-2">
									一句话介绍 ：
								</div>
								<div class="col-md-8">
									<input type="text" class="form-control" value="${settingUser.uWord }" name="uWord" id="uWord" required>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-md-2">
									居住地 ：
								</div>
								<div class="col-md-8">
									<input type="text" class="form-control" value="${settingUser.uResidence }" name="uResidence" id="uResidence" required>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-md-2">
									所在行业 ：
								</div>
								<div class="col-md-8">
									<input type="text" class="form-control" value="${settingUser.uProfession }" name="uProfession" id="uProfession" required>
								</div>
							</div>
							<div>
								<div class="alert " role="alert" id="updateUserMessage"></div>
							</div>
						</div>
						<button id="userSetting" class="btn btn-1">保存</button>
				</div>

				<div id="changePassword" class="tab-pane">
					<div class="heading"><h3>更改密码</h3><br></div>
						<div class="form-group ">
							<div class="form-group row">
								<div class="col-md-2">
									原始密码 ：
								</div>
								<div class="col-md-8">
									<input type="password" class="form-control" placeholder="请输入原密码" name="uPassword"
									   id="uPassword" required>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-md-2">
									新密码 ：
								</div>
								<div class="col-md-8">
									<input type="password" class="form-control" placeholder="请输入新密码" name="uNewPassword1"
									   id="uNewPassword1" required>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-md-2">
									重复密码 ：
								</div>
								<div class="col-md-8">
									<input type="password" class="form-control" placeholder="重复输入新密码" name="uNewPassword2"
									   id="uNewPassword2" required>
								</div>
							</div>
							<div>
								<div class="alert " role="alert" id="updateUserPwdMessage"></div>
							</div>
							<button id="userPwd" class="btn btn-1" name="updateuPassword">修改密码</button>
					</div>
				</div>

				<div id="change1" class="tab-pane">
					<div class="highlight">

					</div>
				</div>

				<div id="change2" class="tab-pane">
					<div class="highlight">

					</div>
				</div>
			</div>

		</div>
	</div>
	
	
	<%@ include file="/WEB-INF/staticSource/footer.jsp"%>
</body>
</html>