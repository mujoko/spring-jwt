package com.mujoko.gcp;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	Logger logger = LoggerFactory.getLogger(AuthController.class);

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		///Query DB and get User
		if ("mujoko".equals(username)) {
			SecureUser userDetail =new SecureUser(username, "ABC",
			new ArrayList<>());
			return userDetail;
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);	
		}
	}
		
}