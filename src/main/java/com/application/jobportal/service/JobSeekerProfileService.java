package com.application.jobportal.service;

import java.util.Optional;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.application.jobportal.entity.JobSeekerProfile;
import com.application.jobportal.entity.Users;
import com.application.jobportal.repository.JobSeekerProfileRepository;
import com.application.jobportal.repository.UsersRepository;

@Service
public class JobSeekerProfileService {

	private final JobSeekerProfileRepository jobSeekerProfileRepository;
	private final UsersRepository usersRepository;
	
	
	


	public JobSeekerProfileService(JobSeekerProfileRepository jobSeekerProfileRepository,
			UsersRepository usersRepository) {
		super();
		this.jobSeekerProfileRepository = jobSeekerProfileRepository;
		this.usersRepository = usersRepository;
	}



	public Optional<JobSeekerProfile> getOne(Integer id){
		return jobSeekerProfileRepository.findById(id);
	}



	public JobSeekerProfile addNew(JobSeekerProfile jobSeekerProfile) {
		
		return jobSeekerProfileRepository.save(jobSeekerProfile);
	}



	public JobSeekerProfile getCurrentSeekerProfile() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			Users user = usersRepository.findByEmail(currentUserName).orElseThrow(() -> new UsernameNotFoundException("User not Found"));
			Optional<JobSeekerProfile> seekerProfile = getOne(user.getUserId());
			return seekerProfile.orElse(null);
		} else return null;
	}
	
}
