package com.application.jobportal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.application.jobportal.entity.UsersType;
//import com.application.jobportal.repository.JobSeekerProfileRepository;
//import com.application.jobportal.repository.RecruiterProfileRepository;
import com.application.jobportal.repository.UsersTypeRepository;
@Service
public class UsersTypeService {

	private final UsersTypeRepository usersTypeRepository;
	

	public UsersTypeService(UsersTypeRepository usersTypeRepository
			) {
		this.usersTypeRepository = usersTypeRepository;
		
		
	}
	
	public List<UsersType> getAll(){
		return usersTypeRepository.findAll();
	}

	
	
}
