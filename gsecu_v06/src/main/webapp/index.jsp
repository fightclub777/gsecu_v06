<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<body>
<div style="text-align:center">
	<h2>Hello Spring Security!</h2><br>
	<sec:authorize access="isAnonymous()">
	<a href="<%=request.getContextPath() %>/user/in">사용자IN</a>&nbsp;&nbsp;&nbsp;
	<a href="<%=request.getContextPath() %>/user/out">사용자OUT</a>&nbsp;&nbsp;&nbsp;
	</sec:authorize>
	<a href="<%=request.getContextPath() %>/home">HOME</a>&nbsp;&nbsp;&nbsp;
	<a href="<%=request.getContextPath() %>/login">LOGIN</a>
</div>
</body>
</html>