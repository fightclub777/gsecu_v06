package net.gongple.gsecu.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import net.gongple.gsecu.domain.User;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired private CustomUserDetailsService userDetailsSvc;
	@Autowired private BCryptPasswordEncoder bcryptPwEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		System.out.println("@@@ Encode Password : "+ bcryptPwEncoder.encode(password));
		
		User user = null;
		Collection<? extends GrantedAuthority> auths = null;
		try {
			user = userDetailsSvc.loadUserByUsername(username);
			
			System.out.println("@@@ input password : "+ password +" , password : "+ user.getPassword());
			if(!bcryptPwEncoder.matches(password, user.getPassword())) {
//			if(!password.equals(user.getPassword())) {
				throw new BadCredentialsException("비밀번호가 맞지 않습니다.");
			} else {
				auths = user.getAuthorities();
			}
		} catch (UsernameNotFoundException e) {
			throw new UsernameNotFoundException(e.getMessage());
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
		return new UsernamePasswordAuthenticationToken(user, password, auths);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
