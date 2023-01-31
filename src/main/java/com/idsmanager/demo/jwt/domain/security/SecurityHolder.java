package com.idsmanager.demo.jwt.domain.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface SecurityHolder {
	UserDetails userDetails();
	
	String username();
}
