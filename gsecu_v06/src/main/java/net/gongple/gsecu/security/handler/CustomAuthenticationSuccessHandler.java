package net.gongple.gsecu.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	private RequestCache reqCache = new HttpSessionRequestCache();
	private String targetUrlParameter;
	private String defaultUrl;
	private boolean useReferer;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	//--- constructor ---//
	public CustomAuthenticationSuccessHandler() {
		targetUrlParameter = "";
		defaultUrl = "/";
		useReferer = false;
	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		
		clearAuthenticationAttributes(request);
		
		int redirectStrategy = decideRedirectStrategy(request, response);
		switch(redirectStrategy) {
		case 1 : 
			useTargetUrl(request, response);
			break;
		case 2 : 
			useSessionUrl(request, response);
			break;
		case 3 :
			useRefererUrl(request, response);
			break;
		default : 
			useDefaultUrl(request, response);
		}
	}
	
	/**
	 * 인증 성공 후 어떤 URL로 Redirect 할지 결정한다.
	 * 요즘은 특별한 경우가 아닌 이상 Session Redirect를 주로 이용하는 것 같다.
	 * 
	 * "Redirect 판단 기준"
	 *    (1) targetUrlParameter의 값을 읽어서 URL값을 체크하고 있으면 Redirect.
	 *    (2) '(1)'의 값이 없으면,
	 *    		Spring Security가 Session에 저장한 URL값을 체크하고 있으면 Redirect.
	 *    (3) '(2)'의 값이 없으면,
	 *    		HttpServletRequest의 REFERER 헤더의 URL값을 체크하고 있으면 Redirect.
	 *    (4) '(3)'의 값이 없으면,
	 *    		Default URL을 Redirect.
	 *         
	 * @param request
	 * @param response
	 * @return	1 : targetUrlParameter 값을 읽은 URL
	 * 			2 : Session에 저장되어 있는 URL
	 * 			3 : REFERER 헤더에 있는 URL
	 * 			4 : Default URL
	 */
	private int decideRedirectStrategy(HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
		
		String targetUrl = request.getParameter(targetUrlParameter);
		if(StringUtils.hasText(targetUrl)) {
			System.out.println("@@@ Custom Success Handler - targetUrl : "+ targetUrl);
			result = 1;
			return result;
		}
		
		SavedRequest savedReq = reqCache.getRequest(request, response);
		if(savedReq != null) {
			System.out.println("@@@ Custom Success Handler - savedReq : "+ savedReq.getRedirectUrl());
			result = 2;
			return result;
		}
		
		String refererUrl = request.getHeader("REFERER");
		if(StringUtils.hasText(refererUrl) && useReferer) {
			System.out.println("@@@ Custom Success Handler - refererUrl : "+ refererUrl);
			result = 3;
			return result;
		}
		
		return result;
	}
	
	private void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
	
	/**
	 * Target URL Setting.
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void useTargetUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SavedRequest savedRequest = reqCache.getRequest(request, response);
		if(savedRequest != null) {
			reqCache.removeRequest(request, response);
		}
		String targetUrl = request.getParameter(targetUrlParameter);
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	/**
	 * Session URL Setting.
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void useSessionUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SavedRequest savedRequest = reqCache.getRequest(request, response);
		String targetUrl = savedRequest.getRedirectUrl();
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	/**
	 * Referer URL Setting.
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void useRefererUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String targetUrl = request.getHeader("REFERER");
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	/**
	 * Default URL Setting.
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void useDefaultUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		redirectStrategy.sendRedirect(request, response, defaultUrl);
	}
	
	//--- setter, getter ---//
	public String getTargetUrlParameter() {
		return targetUrlParameter;
	}

	public void setTargetUrlParameter(String targetUrlParameter) {
		this.targetUrlParameter = targetUrlParameter;
	}

	public String getDefaultUrl() {
		return defaultUrl;
	}

	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}

	public boolean isUseReferer() {
		return useReferer;
	}

	public void setUseReferer(boolean useReferer) {
		this.useReferer = useReferer;
	}
	
}
