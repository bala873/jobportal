package com.application.jobportal.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.application.jobportal.entity.JobPostActivity;
import com.application.jobportal.service.JobPostActivityService;
import com.application.jobportal.service.UsersService;

@Controller
public class JobSeekerApplyControl {

	private final JobPostActivityService jobPostActivityService;
	private final UsersService usersService;
	public JobSeekerApplyControl(JobPostActivityService jobPostActivityService, UsersService usersService) {
		super();
		this.jobPostActivityService = jobPostActivityService;
		this.usersService = usersService;
	}
	@GetMapping("job-details-apply/{id}")
	public String display(@PathVariable("id")int id, Model model) {
		JobPostActivity jobDetails = jobPostActivityService.getOne(id);
		model.addAttribute("jobDetails",jobDetails);
		model.addAttribute("user",usersService.getCurrentUserProfile());
		return "job-details";
	}
	
}
