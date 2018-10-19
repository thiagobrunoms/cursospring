package com.cursomc.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cursomc.constants.UserProfile;

public class MyUserSecurityDetails implements UserDetails {
	
	private Integer id;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	public MyUserSecurityDetails() {
		// TODO Auto-generated constructor stub
	}
	
	

	public MyUserSecurityDetails(Integer id, String email, String password, Set<UserProfile> profiles) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		
		this.authorities = profiles.stream().map(x -> new SimpleGrantedAuthority(x.getName())).collect(Collectors.toList());
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
