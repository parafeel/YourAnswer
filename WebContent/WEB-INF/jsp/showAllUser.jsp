<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show All Users --Answer</title>
</head>
<body>
	<table align='center' border='1' cellspacing='0'>
    <tr>
        <td>uId</td>
        <td>uName</td>
        <td>uEmail</td>
    </tr>
    <c:forEach items="${userList}" var="user" varStatus="st">
        <tr>
            <td>${user.uId}</td>
            <td>${user.uName}</td>
            <td>${user.uEmail}</td>
        </tr>
    </c:forEach>
	</table>
</body>
</html>