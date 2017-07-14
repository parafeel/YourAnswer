<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontResource/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontResource/css/home.css">
<link href="${pageContext.request.contextPath}/frontResource/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/frontResource/bootstrap/js/bootstrap.min.js"></script>


<title>Login or Regist --Answer</title>
</head>
<body>
	<%@ include file="/WEB-INF/staticSource/header.jsp"%>

	<div id="page-content" class="single-page">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="heading"><h2>登录</h2></div>
					<form name="loginform" id="loginform" method="post" action="${pageContext.request.contextPath}/userLogin">
						<div class="form-group">
							<input type="email" class="form-control" placeholder="邮箱" name="ulEmail" id="ulEmail"
								   required>
						</div>
						<div class="form-group">
							<input type="password" class="form-control" placeholder="密码" name="ulPassword"
								   id="ulPassword" required>
						</div>
						<div>
							<div class="alert " role="alert">${loginMessage}</div>
						</div>
						<button type="submit" class="btn btn-1" name="login" id="login">立即登录</button>
						<a href="#">忘记密码 ?</a>
					</form>
				</div>
				
				<div class="col-md-6">
					<div class="heading"><h2>新用户？创建一个账号</h2></div>
					
					<form name="registform" id="registform" method="post" action="userRegister">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="您的真实姓名" name="uRealName" id="realName" >
						</div>
						<div class="form-group">
							<input type="email" class="form-control" placeholder="您的邮箱地址" name="uEmail" id="uEmail" required>
						</div>
						<div class="form-group">
							<input type="tel" class="form-control" placeholder="您的电话号码" name="uTel" id="uTel" >
						</div>
						<div class="form-group">
							<input type="text" class="form-control" placeholder="您的用户名称" name="uName" id="uName" required>
						</div>
						<div class="form-group">
							<input name="uGender" id="umGender" type="radio" value="男"> 男 <input name="uGender"
																								 id="ufGender"
																								 type="radio" value="女"> 女
						</div>
						<div class="form-group">
							<input type="password" class="form-control" placeholder="请输入密码" name="uPassword" id="uPassword" required>
						</div>
						<div class="form-group">
							<input type="password" class="form-control" placeholder="重复输入密码" name="uPassword1" id="uPassword1" required>
						</div>
						<div class="form-group">
							<input name="agree" id="agree" type="checkbox" required> 我同意用户协议
						</div>
						<div>
							<div class="alert " role="alert">${registerMessage}</div>
						</div>
						<button type="submit" class="btn btn-1">立即注册</button>
					</form>
					
				</div>
			</div>
		</div>
	</div>

	
	<%@ include file="/WEB-INF/staticSource/footer.jsp"%>	
</body>
</html>