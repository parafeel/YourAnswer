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

    <script>
        $(document).ready( function() {
            $('#updateuPassword').click(checkPassword);
        });

        function checkPassword() {
            var uNewPassword1 = $('#uNewPassword1').val();
            var uNewPassword2 = $('#uNewPassword2').val();
            if(uNewPassword1.length < 6) {
                $('#updateUserSecurityMessage').addClass("alert-warning");
                $('#updateUserSecurityMessage').html("输入的密码格式不对！");
                return false;
            } else if(uNewPassword1 !== uNewPassword2) {
                $('#updateUserSecurityMessage').addClass("alert-warning");
                $('#updateUserSecurityMessage').html("两次输入的密码不同！");
                return false;
            }
            return true;
        }
    </script>


<title>安全设置-- Answer</title>
</head>
<body>
    <%@ include file="/WEB-INF/staticSource/header.jsp"%>


    <div class="container">
        <br>
        <br>
        <br>
        <div class="highlight">
            <div class="col-lg-4">
                <div class="form-group">
                    <h3>${settingUser.uName }</h3>
                </div>
                <div class="form-group">
                    <h6>${settingUser.uWord }</h6>
                </div>

                <ul class="nav nav-tabs">
                    <li class="active"><a href="#changePassword" rel="external nofollow" data-toggle="tab" >更改密码</a></li>
                    <li><a href="#change1" rel="external nofollow" data-toggle="tab">change1</a></li>
                    <li><a href="#change2" rel="external nofollow" data-toggle="tab">change2</a></li>
                </ul>
                <div class="tab-content"><!--选项卡的内容-->
                    <div id="changePassword" class="active tab-pane">
                        <form name="loginform" id="loginform" method="post" action="${pageContext.request.contextPath}/updateUserSecurity">
                            <div class="form-group">
                            </div>
                            <div class="form-group">
                                <input type="hidden" class="form-control" value="${settingUser.uId}" name="uId"
                                                                     id="uId">
                                <input type="hidden" class="form-control" value="${settingUser.uEmail }" name="uEmail"
                                       id="uEmail" >
                                <input type="password" class="form-control" placeholder="请输入原密码" name="uPassword"
                                       id="uPassword" required>
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" placeholder="请输入新密码" name="uNewPassword1"
                                       id="uNewPassword1" required>
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" placeholder="重复输入新密码" name="uNewPassword2"
                                       id="uNewPassword2" required>
                            </div>
                            <div>
                                <div class="alert " role="alert" id="updateUserSecurityMessage">${updateUserSecurityMessage}</div>
                            </div>
                            <button type="submit" class="btn btn-1" name="updateuPassword" id="updateuPassword" >修改密码</button>


                        </form>
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
    </div>




    <%@ include file="/WEB-INF/staticSource/footer.jsp"%>
</body>
</html>
