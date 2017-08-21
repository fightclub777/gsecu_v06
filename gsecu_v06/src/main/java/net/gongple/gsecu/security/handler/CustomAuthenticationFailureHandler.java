package net.gongple.gsecu.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
	
	private String loginIdTagName;			// 로그인 아이디에 해당하는 input 태그의 name 값. 
	private String loginPwTagName;			// 로그인 비밀번호에 해당하는 input 태그의 name 값.
	private String loginRedirectTagName;	// 로그인 성공시 Redirect URL에 해당하는 input 태그의 name 값.
	private String exceptionMsgKey;			// 예외 메시지를 request의 Attribute에 저장할 때 사용될 Key 값.
	private String defaultFailureUrl;		// 화면에 보여줄 URL.(로그인 화면)
	
	
	//--- constructor ---//
	public CustomAuthenticationFailureHandler() {
		this.loginIdTagName = "userid";
		this.loginPwTagName = "userpw";
		this.loginRedirectTagName = "loginRedirect";
		this.exceptionMsgKey = "securityExceptionMsg";
		this.defaultFailureUrl = "/login";
	}
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		
		/*
		 * Request 객체의 Attribute에 사용자가 인증 실패시 입력했던
		 * 로그인 아이디와 비밀번호를 저장해두어 로그인 페이지에서 이를 접근하도록 한다.
		*/
		String loginId = request.getParameter(getLoginIdTagName());
		String loginPw = request.getParameter(getLoginPwTagName());
		String loginRedirectUrl = request.getParameter(getLoginRedirectTagName());
		
		System.out.println("@@@ Custom Failure Handler - loginIdTagName : "+ loginId);
		System.out.println("@@@ Custom Failure Handler - loginPwTagName : "+ loginPw);
		System.out.println("@@@ Custom Failure Handler - loginRedirectTagName : "+ loginRedirectUrl);
		
		request.setAttribute(getLoginIdTagName(), loginId);
		request.setAttribute(getLoginPwTagName(), loginPw);
		request.setAttribute(getLoginRedirectTagName(), loginRedirectUrl);
		
		request.setAttribute(getExceptionMsgKey(), authException.getMessage());
		request.getRequestDispatcher(getDefaultFailureUrl()).forward(request, response);
	}

	//--- getter, setter ---//
	public String getLoginIdTagName() {
		return loginIdTagName;
	}

	public void setLoginIdTagName(String loginIdTagName) {
		this.loginIdTagName = loginIdTagName;
	}

	public String getLoginPwTagName() {
		return loginPwTagName;
	}

	public void setLoginPwTagName(String loginPwTagName) {
		this.loginPwTagName = loginPwTagName;
	}

	public String getLoginRedirectTagName() {
		return loginRedirectTagName;
	}

	public void setLoginRedirectTagName(String loginRedirectTagName) {
		this.loginRedirectTagName = loginRedirectTagName;
	}

	public String getExceptionMsgKey() {
		return exceptionMsgKey;
	}

	public void setExceptionMsgKey(String exceptionMsgKey) {
		this.exceptionMsgKey = exceptionMsgKey;
	}

	public String getDefaultFailureUrl() {
		return defaultFailureUrl;
	}

	public void setDefaultFailureUrl(String defaultFailureUrl) {
		this.defaultFailureUrl = defaultFailureUrl;
	}
	
}
