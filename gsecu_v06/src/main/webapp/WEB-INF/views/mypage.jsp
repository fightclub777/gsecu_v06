<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<div style="text-align:center">
	<h2>[MyPage] "${nickName}"님 안녕하세요~ ^^</h2><br>
	<jsp:include page="./fragments/menu.jsp"/>
	<a href="<%=request.getContextPath() %>/home">HOME</a>&nbsp;&nbsp;&nbsp;
	<a href="<%=request.getContextPath() %>/logout">LOGOUT</a>
</div>
</body>
</html>