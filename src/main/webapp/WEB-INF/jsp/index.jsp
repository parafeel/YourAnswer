<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontResource/css/home.css">
<link href="${pageContext.request.contextPath}/frontResource/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js" charset="utf-8" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/frontResource/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>



<style type="text/css">
	html{height: 100%}
	body{margin: 0;height: 100%;
		background: #fff;}
	canvas{display: block;width: 100%;height: 100%;}
	.welcome-text{
		position: absolute;
		top:10%;
		left: 10%;
		width: 40%;
		height: 20%;
		opacity: 1;
	}
	#list {
		position: absolute;
		top:30%;
		width: 80%;
		margin-left: 10%;
		margin-right: 10%;
	}
	.highlight {
		padding-right: 15px;
		padding-left: 15px;
		margin-right: auto;
		margin-left: auto;
	}
</style>

	<title>Welcome to Answer</title>
</head>
<body>
	<%@ include file="/WEB-INF/staticSource/header.jsp"%>
	<div class="row">
		<div class="welcome-text col-md-9">
			<div class="alert alert-info">
				<ul class="nav nav-pills">
					<li role="presentation"><a href="${pageContext.request.contextPath}/makeQuestion">提问</a></li>
					<li role="presentation"><a href="#">回答</a></li>
					<li role="presentation"><a href="${pageContext.request.contextPath}/makeEssay">写随笔</a></li>
				</ul>
			</div>
			<hr>
		</div>
	</div>
	<div id="list">

	</div>
	
	<%@ include file="/WEB-INF/staticSource/footer.jsp"%>

	<script type="text/javascript">

        $(document).ready(function () {
                $.ajax({
                    type: 'GET',
                    headers: {
                        Accept: 'application/json; charset=utf-8',
                        "Content-Type": "application/json; charset=utf-8"
                    },
                    url: '/YourAnswer/index',
                    success: function (data) {
                        loadData(data);
                    }, error: function (e) {
                        alert('Error: ' + e);
                    }
                })
        });

        function loadData(data) {
            $.each(data, function (index, question) {
                //循环获取数据
                var qId = question.qId;
                var qTitle = question.qTitle;
                var qDetail = question.qDetail;
                var qMadeByUserId = question.qMadeByUserId;
                var qMadeByUser = question.qMadeByUser;
                var qMadeDate = question.qMadeDate;
                var uName = qMadeByUser.uName;
                var date=new Date(qMadeDate).toLocaleString();

                var list = $('#list');
                list.append(
                    '<div><div class="highlight" style="background-color: #f6f6f6;">' +
                    '<div class="form-group smallInfo" style="color:#D0D0D0">' +
                    '<h6><a style="color:#999999" href="${pageContext.request.contextPath}/showUser/' +
                    qMadeByUserId +
                    '" target="_blank">' + uName + '</a> &nbsp;提出了问题：</h6>' +
                    '</div> <hr> <div class="form-group">' +
                    '<h3><a href="${pageContext.request.contextPath}/Question/' + qId + ' "> ' + qTitle + '</a></h3>' +
                    '</div> <div class="form-group"> <p>' + qDetail + '</p> </div> <div class="form-group"  style="color:#999999">' +
                    '<hr><h6>发布于：' + date +'</div></div></div> <br>'
                );
            });
        }

	</script>



	
</body>
</html>