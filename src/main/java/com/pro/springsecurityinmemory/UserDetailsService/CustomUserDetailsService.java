package com.pro.springsecurityinmemory.UserDetailsService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pro.springsecurityinmemory.UserDetails.CustomUserDetails;

public class CustomUserDetailsService implements UserDetailsService{
	
	private Map<String,UserDetails> usersList = new HashMap<>();
	
	public CustomUserDetailsService() {
		
	}
	
	public CustomUserDetailsService(UserDetails... users) {
		for(UserDetails user:users)usersList.put(user.getUsername(), user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(username);
		
		if(usersList.containsKey(username)) {
			System.out.println("UserName contains in the list");
			System.out.println("password : " + usersList.get(username).getPassword());
			new CustomUserDetails(usersList.get(username));
			return	usersList.get(username);
		}
		else {
			System.out.println("UserName is not exist in the list");
			throw new UsernameNotFoundException("No user found");
		}
	}
	
}





