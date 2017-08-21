package net.gongple.gsecu.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.gongple.gsecu.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/root-context.xml", "classpath:spring/security-context.xml"})

public class UserServiceTest {
	
	@Autowired UserService usrSvc;
	@Autowired BCryptPasswordEncoder bcryptPwEncoder;
	
	private List<User> makeInputData() {
		List<User> inputDatas = new ArrayList<User>();
		User user = new User();
		user.setUserId("adminh");
		user.setUserPwd(bcryptPwEncoder.encode("1234h"));
		user.setNickName("최영자");
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);
		inputDatas.add(user);
		
		user = new User();
		user.setUserId("adminm");
		user.setUserPwd(bcryptPwEncoder.encode("1234m"));
		user.setNickName("중영자");
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);
		inputDatas.add(user);
		
		user = new User();
		user.setUserId("adminl");
		user.setUserPwd(bcryptPwEncoder.encode("1234l"));
		user.setNickName("실영자");
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);
		inputDatas.add(user);
		
		user = new User();
		user.setUserId("user");
		user.setUserPwd(bcryptPwEncoder.encode("1234u"));
		user.setNickName("걍유저");
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);
		inputDatas.add(user);
		
		user = new User();
		user.setUserId("adminuser");
		user.setUserPwd(bcryptPwEncoder.encode("1234au"));
		user.setNickName("실영유");
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);
		inputDatas.add(user);
		
		return inputDatas;
	}
	
	@Test
	public void User입력() throws Exception {
		List<User> inputData = makeInputData();
		System.out.println("@@@ inputData - Size : "+ inputData.size());
		
		for(User user : inputData) {
			System.out.println("@");
			System.out.println("@ Id: "+ user.getId() +", UserId: "+ user.getUserId() +", UserPwd: "+ user.getUserPwd() +", NickName: "+ user.getNickName());
			usrSvc.add(user);
		}
		
		List<User> users = usrSvc.findAll();
		for(User user : users) {
			System.out.println("#");
			System.out.println("# Id: "+ user.getId() +", UserId: "+ user.getUserId() +", UserPwd: "+ user.getUserPwd() +", NickName: "+ user.getNickName());
		}
	}
}
