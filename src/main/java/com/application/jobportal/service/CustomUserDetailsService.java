package com.application.jobportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.application.jobportal.entity.Users;
import com.application.jobportal.repository.UsersRepository;
import com.application.jobportal.util.CustomUserDetails;
@Service
public class CustomUserDetailsService implements UserDetailsService{

	private final UsersRepository usersRepository;
	@Autowired
	public CustomUserDetailsService(UsersRepository usersRepository) {
		super();
		this.usersRepository = usersRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = usersRepository.findByEmail(username).orElseThrow(()-> new
				UsernameNotFoundException("Could Not Found User"));
		return new CustomUserDetails(user);
	}

}
