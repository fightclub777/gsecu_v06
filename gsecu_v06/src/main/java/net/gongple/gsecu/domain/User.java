package net.gongple.gsecu.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "user")
@TableGenerator(name = "base_id_sequence", table = "id_sequences", 
	pkColumnName = "id_type", pkColumnValue= "BASE", allocationSize = 1)
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.TABLE, generator = "base_id_sequence")
	@Column(name = "user_id")
	private Long id;
	
	@Column(name = "username", nullable = false)
	private String userId;
	
	@Column(name = "passwd", nullable = false)
	private String userPwd;
	
	@Column(name = "nickname", nullable = false)
	private String nickName;
	
	@Column(name = "acc_expired", nullable = false)
	private boolean accountNonExpired;
	
	@Column(name = "acc_locked", nullable = false)
	private boolean accountNonLocked;
	
	@Column(name = "cre_expired", nullable = false)
	private boolean credentialsNonExpired;
	
	@Column(name = "enabled", nullable = false)
	private boolean enabled;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Authority> auths = new ArrayList<Authority>();
	
	
	//--- constructor ---//
	public User() {}
	
	//--- override ---//
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getAuths();
	}

	@Override
	public String getPassword() {
		return getUserPwd();
	}

	@Override
	public String getUsername() {
		return getUserId();
	}

	@Override
	public boolean isAccountNonExpired() {
		//계정 만료 여부를 리턴.(true이면 만료되지 않음)
		return accountNonExpired;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		//계정 잠김 여부를 리턴.(true이면 잠겨있지 않음)
		return accountNonLocked;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		//계정 패스워드 만료 여부를 리턴.(true이면 만료되지 않음)
		return credentialsNonExpired;
	}
	
	@Override
	public boolean isEnabled() {
		//계정의 사용가능 여부를 리턴.(true이면 사용가능)
		return enabled;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", userPwd=" + userPwd + ", nickName=" + nickName + ", auths="
				+ auths + "]";
	}
	
	//--- setter, getter ---//
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public List<Authority> getAuths() {
		return auths;
	}

	public void setAuths(List<Authority> auths) {
		this.auths = auths;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
