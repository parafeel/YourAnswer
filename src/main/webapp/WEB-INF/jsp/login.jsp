<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontResource/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontResource/css/home.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/frontResource/JS/jquery-3.2.1.min.js"></script>

<link href="${pageContext.request.contextPath}/frontResource/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/frontResource/bootstrap/js/bootstrap.min.js"></script>

<script>
    $(document).ready( function() {
        $('#login').click(checkLogin);
        $('#captchaImage').click(function()
        {
            $('#captchaImage').attr("src", "captcha.form?timestamp=" + (new Date()).valueOf());
        });
    });
    function checkLogin() {
        var ulEmail = $('#ulEmail').val();
        var ulPassword = $('#ulPassword').val();
        var verificationCode = $('#VerificationCode').val();
        if( ulEmail.length == 0 || ulPassword == 0 ) {
            $('#loginMessage').addClass("alert-warning");
            $('#loginMessage').html("请输入账号密码！");
            return false;
		}
        if(verificationCode.length != 6 ) {
            $('#loginMessage').addClass("alert-warning");
            $('#loginMessage').html("验证码不正确！");
            return false;
		}
        if(ulPassword.length < 6) {
            $('#loginMessage').addClass("alert-warning");
            $('#loginMessage').html("密码格式不正确!");
            return false;
		}
		return true;
    }
</script>

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

						<div class="form-group">
							<input type="text" class="form-control" placeholder="验证码" name="VerificationCode" id="VerificationCode"
								   required>
						</div>
						<div class="row">
							<div class="form-group col-md-4">
								<img id="captchaImage" src="captcha.form"/>
							</div>
							<div class="form-group col-md-8">
								<div class="alert" role="alert" id="loginMessage">${loginMessage}</div>
							</div>
						</div>
						<div>
							<button type="submit" class="btn btn-1" name="login" id="login" >
								立即登录</button>
							<a href="#">  忘记密码 ?</a>
						</div>
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