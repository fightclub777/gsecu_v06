package net.gongple.gsecu.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import net.gongple.gsecu.domain.User;
import net.gongple.gsecu.service.UserService;

public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired UserService usrSvc;
	
	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = usrSvc.findByUserId(username);
		if(user == null) throw new UsernameNotFoundException("사용자 아이디를 찾을 수 없습니다.");
		
		return user;
	}
	
}
