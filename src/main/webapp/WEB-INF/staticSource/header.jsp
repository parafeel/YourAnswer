<%@page import="ssm.pojo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-headr">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">导航切换</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index">Home</a>
            </div>
            
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                <!-- 此处地址为/projectName/index，不能index原因是可能进入下一层url而出错 -->        	
                    <li><a href="${pageContext.request.contextPath}/index">首页</a></li>
                    <li><a href="${pageContext.request.contextPath}/topicCenter">主题</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">参与<span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="${pageContext.request.contextPath}/makeQuestion">提问</a></li>
                            <li class="divider"></li>
                            <li><a href="${pageContext.request.contextPath}/listQuestion">回答</a></li>
                            <li class="divider"></li>
                            <li><a href="${pageContext.request.contextPath}/makeArtile">文章</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">关于<span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="${pageContext.request.contextPath}/aboutUs">网站介绍</a></li>
                            <li class="${pageContext.request.contextPath}/divider"></li>
                            <li><a href="${pageContext.request.contextPath}/connectUs">联系我们</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                    	<a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user">
                    		<% 	User user = (User)session.getAttribute("currentUser");
								if ( user == null ) {
							%>
									未登录
						</span><span class="caret "></span></a>
                    	<ul class="dropdown-menu" role="menu">
                            <li><a href="${pageContext.request.contextPath}/login"><span class="glyphicon glyphicon-log-in"></span> 登录/注册</a></li>
                        </ul>
							<%
								}
								else {
							%>
									${currentUser.uName}
						<span class="caret "></span></a>
                    	<ul class="dropdown-menu" role="menu">
                            <li><a href="${pageContext.request.contextPath}/showUser/${currentUser.uId}"><span class="glyphicon glyphicon-stats"></span> 个人主页</a></li>
                        	<li><a href="${pageContext.request.contextPath}/setting/${currentUser.uId}"><span class="glyphicon glyphicon-wrench"></span> 账号设置</a></li>
                        	<li><a href="${pageContext.request.contextPath}/userLogout"><span class="glyphicon glyphicon-off"></span> 退出登录</a></li>
                        </ul>
							<%
								}
							%>
                    	
                    </li>
                </ul>
                <form class="navbar-form navbar-right">
                    <input type="text" class="form-control" value="" placeholder="搜索">
                </form>
            </div>
        </div>
</nav>