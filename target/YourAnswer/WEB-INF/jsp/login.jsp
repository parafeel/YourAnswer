<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontResource/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontResource/css/home.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/frontResource/JS/jquery-3.2.1.min.js"></script>
<link href="${pageContext.request.contextPath}/frontResource/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/frontResource/bootstrap/js/bootstrap.min.js"></script>


<script type="text/javascript" src="${pageContext.request.contextPath}/frontResource/JS/loginAjax.js"></script>
<title>Login or Regist --Answer</title>
</head>
<body>
	<%@ include file="/WEB-INF/staticSource/header.jsp"%>


	<div class="container">
	<div id="page-content" class="single-page">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="heading"><h2>登录</h2></div>
						<div class="form-group">
							<input type="email" class="form-control" placeholder="邮箱" name="ulEmail" id="ulEmail"
								   required>
						</div>
						<div class="form-group">
							<input type="password" class="form-control" placeholder="密码" name="ulPassword"
								   id="ulPassword" required>
						</div>

						<div class="form-group">
							<input type="text" class="form-control" placeholder="验证码" name="VerificationCode" id="VerificationCode"
								   required>
						</div>
						<div class="row">
							<div class="form-group col-md-4">
								<img id="captchaImage" src="captcha.form"/>
							</div>
							<div class="form-group col-md-8">
								<div class="alert" role="alert" id="loginMessage"></div>
							</div>
						</div>
						<div>
							<button class="btn btn-1" name="login" id="login" >
								立即登录</button>
							<a href="#">  忘记密码 ?</a>
						</div>
				</div>
				
				<div class="col-md-6">
					<div class="heading"><h2>新用户？创建一个账号</h2></div>

						<div class="form-group">
							<input type="text" class="form-control" placeholder="您的真实姓名" name="uRealName" id="uRealName" required>
						</div>
						<div class="form-group">
							<input type="email" class="form-control" placeholder="您的邮箱地址" name="urEmail" id="urEmail"
								   required>
						</div>
						<div class="form-group">
							<input type="tel" class="form-control" placeholder="您的电话号码" name="uTel" id="uTel" required>
						</div>
						<div class="form-group">
							<input type="text" class="form-control" placeholder="您的用户名称" name="uName" id="uName" required>
						</div>
						<div class="form-group">
							<input name="uGender" id="umGender" type="radio" value="男"> 男
							<input name="uGender" id="ufGender" type="radio" value="女"> 女
						</div>
						<div class="form-group">
							<input type="password" class="form-control" placeholder="请输入密码" name="urPassword"
								   id="urPassword" required>
						</div>
						<div class="form-group">
							<input type="password" class="form-control" placeholder="重复输入密码" name="urPassword1"
								   id="urPassword1" required>
						</div>
						<div class="form-group">
							<input name="agree" id="agree" type="checkbox" required> 我同意用户协议
						</div>
						<div>
							<div class="alert " role="alert" id="registerMessage"></div>
						</div>
						<button class="btn btn-1" name="register" id="register">立即注册</button>
					
				</div>
			</div>
		</div>
	</div>
	<div class="container">
	
	<%@ include file="/WEB-INF/staticSource/footer.jsp"%>
</body>
</html>