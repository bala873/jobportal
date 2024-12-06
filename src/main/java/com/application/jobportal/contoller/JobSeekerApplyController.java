package com.application.jobportal.contoller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.application.jobportal.entity.JobPostActivity;
import com.application.jobportal.entity.JobSeekerApply;
import com.application.jobportal.entity.JobSeekerProfile;
import com.application.jobportal.entity.JobSeekerSave;
import com.application.jobportal.entity.RecruiterProfile;
import com.application.jobportal.entity.Users;
import com.application.jobportal.service.JobPostActivityService;
import com.application.jobportal.service.JobSeekerApplyService;
import com.application.jobportal.service.JobSeekerProfileService;
import com.application.jobportal.service.JobSeekerSaveService;
import com.application.jobportal.service.RecruiterProfileService;
import com.application.jobportal.service.UsersService;

@Controller
public class JobSeekerApplyController {

	private final JobPostActivityService jobPostActivityService;
	private final UsersService usersService;
	private final JobSeekerApplyService jobSeekerApplyService;
	private final JobSeekerSaveService jobSeekerSaveService;
	private final RecruiterProfileService recruiterProfileService;
	private final JobSeekerProfileService jobSeekerProfileService;
	
	 
	
	
	
	public JobSeekerApplyController(JobPostActivityService jobPostActivityService, UsersService usersService,
			JobSeekerApplyService jobSeekerApplyService, JobSeekerSaveService jobSeekerSaveService,
			RecruiterProfileService recruiterProfileService, JobSeekerProfileService jobSeekerProfileService) {
		super();
		this.jobPostActivityService = jobPostActivityService;
		this.usersService = usersService;
		this.jobSeekerApplyService = jobSeekerApplyService;
		this.jobSeekerSaveService = jobSeekerSaveService;
		this.recruiterProfileService = recruiterProfileService;
		this.jobSeekerProfileService = jobSeekerProfileService;
	}



	@GetMapping("job-details-apply/{id}")
	public String display(@PathVariable("id")int id, Model model) {
		JobPostActivity jobDetails = jobPostActivityService.getOne(id);
		List<JobSeekerApply> jobSeekerApplyList = jobSeekerApplyService.getJobCandidates(jobDetails);
		List<JobSeekerSave> jobSeekerSaveList = jobSeekerSaveService.getJobCandidates(jobDetails);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("Recruiter"))) {
				RecruiterProfile user = recruiterProfileService.getCurrentRecruiterProfile();
				if (user != null) {
					model.addAttribute("applyList",jobSeekerApplyList);
				}
			}else {
				JobSeekerProfile user = jobSeekerProfileService.getCurrentSeekerProfile();
				if (user != null) {
					boolean exists = false;
					boolean saved = false;
					for(JobSeekerApply jobSeekerApply:jobSeekerApplyList) {
						if(jobSeekerApply.getUserId().getUserAccountId() == user.getUserAccountId()) {
							exists = true;
							break;
						}
					}
					for(JobSeekerSave jobSeekerSave : jobSeekerSaveList) {
						if(jobSeekerSave.getUserId().getUserAccountId() == user.getUserAccountId()) {
							saved = true;
							break;
						}
					}
					model.addAttribute("alreadyApplied",exists);
					model.addAttribute("alreadySaved",saved);
				}
				
			}
		}
		
		JobSeekerApply jobSeekerApply = new JobSeekerApply();
		model.addAttribute("applyJob",jobSeekerApply);
		
		model.addAttribute("jobDetails",jobDetails);
		model.addAttribute("user",usersService.getCurrentUserProfile());
		return "job-details";
	}
	
	@PostMapping("/job-details/apply/{id}")
	public String apply(@PathVariable("id") int id,JobSeekerApply jobSeekerApply) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			Users user = usersService.findByEmail(currentUserName);
			Optional<JobSeekerProfile> seekerProfile = jobSeekerProfileService.getOne(user.getUserId());
			JobPostActivity jobPostActivity = jobPostActivityService.getOne(id);
			if (seekerProfile.isPresent() && jobPostActivity != null) {
			    jobSeekerApply = new JobSeekerApply();
			    jobSeekerApply.setUserId(seekerProfile.get());
			    jobSeekerApply.setJob(jobPostActivity);
			    jobSeekerApply.setApplyDate(new Date(0));
			}else {
				throw new RuntimeException("User not Found");
			}
			jobSeekerApplyService.addNew(jobSeekerApply);
		}
		
		return "redirect:/dashboard/";
	}
	
}
