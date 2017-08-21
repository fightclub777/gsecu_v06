<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<div style="text-align:center">
	<h2>Login!</h2><br>
	<form name="loginForm" method="POST">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<table style="margin-left:auto; margin-right:auto;">
			<tr>
				<td>아이디:</td>
				<td><input type="text" name="userid" value="${userid }"></td>
			</tr>
			<tr>
			<td>비밀번호:</td>
				<td><input type="password" name="userpw" value="${userpw }"></td>
			</tr>
			<!-- <tr>
				<td></td>
				<td>Remember Me: <input type="checkbox" name="remember-me" /></td>
			</tr> -->
			<c:if test="${not empty securityExceptionMsg }">
			<tr>
				<td></td>
				<td>${securityExceptionMsg }</td>
			</tr>
			</c:if>
			<tr>
				<td></td>
				<td>
					<input name="submit" type="submit" value="로그인" />
				</td>
			</tr>
		</table>
		<!-- 
		loginRedirect의 값이 존재하면, 
		인증 후 loginRedirect의 값으로 설정된 URL로 무조건 이동한다.				
		-->
		<!-- <input type="hidden" name="loginRedirect" value="/home" /> -->
		<input type="hidden" name="loginRedirect" value="${loginRedirect }" />
	</form>
	<a href="<c:url value='/'/>">INDEX</a>
</div>
</body>
</html>