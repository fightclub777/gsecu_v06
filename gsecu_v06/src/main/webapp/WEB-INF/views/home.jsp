<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<div style="text-align:center">
	<h2>[HOME] ${msg}</h2><br>
	<jsp:include page="./fragments/menu.jsp"/>
	<a href="<%=request.getContextPath() %>/mypage">MyPage</a>&nbsp;&nbsp;&nbsp;
	<a href="<%=request.getContextPath() %>/logout">LOGOUT</a>
</div>
</body>
</html>