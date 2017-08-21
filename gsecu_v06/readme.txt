#gsecu_v0.6

1. 개요.
	gsecu_v0.6은 Spring Security가 담당했던 인증 성공과 인증 실패를 처리하는 커스텀 핸들러를 구현한다.

2. 목표.
	1) 인증 성공을 담당하는 AuthenticationSuccessHandler 인터페이스를 구현하는 클래스 작성.
	2) 인증 실패를 담당하는 AuthenticationFailureHandler 인터페이스를 구현하는 클래스 작성.
	3) 비밀번호 암호화를 담당하는 BCryptPasswordEncoder 클래스를 인증에 적용.
	
3. 주요내용.
	
	3.1. AuthenticationSuccessHandler 인터페이스.
		: 구현 클래스는 net.gongple.gsecu.security.handler.CustomAuthenticationSuccessHandler
		
		다음 메소드를 구현해야 한다.
		onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
		
		메소드의 파라메터를 보면 HttpServletRequest, HttpServletResponse, Authentication의 세가지가 있는데,
		HttpServletRequest를 통해 http요청에 대한 모든 데이터를 얻어낼 수가 있으며,
		HttpServletResponse를 통해 응답 방식 또한 원하는대로 정할 수 있다.
		Authentication을 통해 사용자의 데이터 또한 얻을 수 있다.(인증 성공을 했을 시 이 메소드를 실행하기 때문)
		
	3.2. AuthenticationFailureHandler 인터페이스.
		: 구현 클래스는 net.gongple.gsecu.security.handler.CustomAuthenticationFailureHandler
		
		다음 메소드를 구현해야 한다.
		onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
		
		메소드의 파라메터를 보면 HttpServletRequest, HttpServletResponse, AuthenticationException의 세가지가 있는데,
		HttpServletRequest, HttpServletResponse는 3.1.과 동일하지만 마지막 파라메터는 AuthenticationException이다.
		즉, 인증 실패시에 발생한 AuthenticationException을 돌려주는 것이다.
		
	3.3. BCryptPasswordEncoder 클래스.
		: security-context.xml에 bean으로 등록하고 인증과 관련한 곳에서 불러다 쓰도록 한다.
		텍스트를 암호화하는 것은 encode(String text) 메소드가 담당하며,
		텍스트와 암호화된 텍스트를 비교하는 것은 matches(String text, String encodeText)가 담당한다.
		비교의 결과가 참이면 true, 거짓이면 false를 반환한다.
		
4. 추가내용.
	4.1. 
		1) 
			: 
		
		2) 
			: 
			
	4.2. 
		1) 
