package com.pro.SpringSecurityJWTAuth;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pro.SpringSecurityJWTAuth.model.AuthenticationRequest;
import com.pro.SpringSecurityJWTAuth.model.AuthenticationResponse;
import com.pro.SpringSecurityJWTAuth.services.MyUserDetailsService;
//import com.pro.SpringSecurityJWTAuth.util.JwtUtil;
import com.pro.SpringSecurityJWTAuth.util.JwtUtil;

@RestController
public class HomeResource {
	
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	MyUserDetailsService userDetailsService;
	@Autowired
	JwtUtil jwtTockenUtil;
	
	
	@RequestMapping(value="/home" )
	public String getHome() {
		return "Welcome";
	}
	
	@RequestMapping(value="/authenticate",method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationTocken(
			    @RequestBody AuthenticationRequest authenticationRequest)throws Exception{
//		try {
//		authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassWord()));
//	    }
//		catch (BadCredentialsException e) {
//			 throw new Exception("Incorrect Username password",e);
//		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
		final String jwt = jwtTockenUtil.generateTocken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

}












