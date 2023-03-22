package com.bowwow.customer.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bowwow.common.entity.User;
import com.bowwow.customer.user.UserRepository;


public class BowwowUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		User user = userRepo.findByEmail(email);
		if(user != null) {
			return new BowwowUserDetails(user);
		}
		throw new UsernameNotFoundException("Could not find user with email: " + email);
	}
}
