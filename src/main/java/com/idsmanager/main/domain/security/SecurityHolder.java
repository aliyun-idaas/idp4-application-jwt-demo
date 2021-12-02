package com.idsmanager.main.domain.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface SecurityHolder {
	UserDetails userDetails();
	
	String username();
}
