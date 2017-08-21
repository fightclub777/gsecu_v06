<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
	<sec:authorize access="hasRole('ROLE_H_ADMIN')">
	<a href="<%=request.getContextPath() %>/admin/h">최고관리자</a>&nbsp;&nbsp;&nbsp;
	</sec:authorize>
	<sec:authorize access="hasAnyRole('ROLE_M_ADMIN', 'ROLE_H_ADMIN')">
	<a href="<%=request.getContextPath() %>/admin/m">중간관리자</a>&nbsp;&nbsp;&nbsp;
	</sec:authorize>
	<sec:authorize access="hasAnyRole('ROLE_L_ADMIN', 'ROLE_M_ADMIN', 'ROLE_H_ADMIN')">
	<a href="<%=request.getContextPath() %>/admin/l">실무관리자</a>&nbsp;&nbsp;&nbsp;
	</sec:authorize>
	<sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_L_ADMIN', 'ROLE_M_ADMIN', 'ROLE_H_ADMIN')">
	<a href="<%=request.getContextPath() %>/user/in">사용자IN</a>&nbsp;&nbsp;&nbsp;
	<a href="<%=request.getContextPath() %>/user/out">사용자OUT</a>&nbsp;&nbsp;&nbsp;
	</sec:authorize>
	<br><br>
</sec:authorize>