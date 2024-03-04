package com.pro.springsecurityinmemory.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.pro.springsecurityinmemory.UserDetailsService.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		System.out.println("Inside PasswordEncoder");
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityConfig(HttpSecurity http) throws Exception {
		     
		     System.out.println("Inside SecurityFilterChain ");
		     return http.csrf().disable()
		                .authorizeHttpRequests(auth->{
		                	try {
								auth
								.requestMatchers("/persons").authenticated()
								    .anyRequest().permitAll()
								.anyRequest().hasAnyRole("ADMIN","USER")
								    .and().formLogin();
							} catch (Exception e) {
								e.printStackTrace();
							}
		                }).build();
//		     return http.csrf().disable()
//		    		    .authorizeHttpRequests()
////		    		    .requestMatchers("/persons").authenticated()
//		    		    .anyRequest().authenticated()
//		    		    .and().formLogin().and().build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		UserDetails user1 = User.withUsername("admin")
				                      .password(passwordEncoder().encode("admin"))
				                      .roles("ADMIN")
				                      .build();
		
		UserDetails user2 = User.withUsername("user")
						                .password(passwordEncoder().encode("user"))
						                .roles("USER")
						                .build();
//		
//		return  new InMemoryUserDetailsManager(user1,user2);
		System.out.println("Inside UserDetailsService");
		return new CustomUserDetailsService(user1,user2);
		
	}
	
	@Bean
	public AuthenticationProvider getauthenication() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(passwordEncoder());
//		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		System.out.println("Inside AuthenticationProvider provider");
		return provider;
	}
	
}






