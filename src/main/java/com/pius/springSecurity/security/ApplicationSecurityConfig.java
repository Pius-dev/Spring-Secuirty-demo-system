package com.pius.springSecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.pius.springSecurity.security.ApplicationUserPermission.*;
import static com.pius.springSecurity.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {

		this.passwordEncoder = passwordEncoder;
	}
	
	
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
		.authorizeHttpRequests()
		.antMatchers("/", "index", "/css/*", "/js/*").permitAll()
				.antMatchers("/api/**").hasRole(STUDENT.name())
				.antMatchers(HttpMethod.PUT,"management/api/**").hasAuthority(COURSE_WRITE.getPermission())
				.antMatchers(HttpMethod.DELETE,"management/api/**").hasAuthority(COURSE_WRITE.getPermission())
				.antMatchers(HttpMethod.POST,"management/api/**").hasAuthority(COURSE_WRITE.getPermission())
				.antMatchers(HttpMethod.GET,"management/api/**").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name())
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
		
		
		
		
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		 UserDetails estherUser = User.builder()
		.username("esther")
		.password(passwordEncoder.encode("password"))
		.roles(STUDENT.name()) // ROLE_STUDENT
				 .authorities(STUDENT.getGrantedAuthorities())
		.build();
		 
		 
		 UserDetails piusUser = User.builder()
		 	.username("pius")
		 	.password(passwordEncoder.encode("password123"))
		 	.roles(ADMIN.name())
				 .authorities(ADMIN.getGrantedAuthorities())
		 	.build();

		UserDetails tomUser = User.builder()
				.username("tom")
				.password(passwordEncoder.encode("password123"))
				.roles(ADMINTRAINEE.name()) //ADMINTRAINEE
				.authorities(ADMINTRAINEE.getGrantedAuthorities())
				.build();
		return new InMemoryUserDetailsManager(
				estherUser,
				piusUser,
				tomUser
				);
		
	}
	
	
	


}
