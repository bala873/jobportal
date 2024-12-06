package com.application.jobportal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.application.jobportal.entity.JobPostActivity;
import com.application.jobportal.entity.JobSeekerApply;
import com.application.jobportal.entity.JobSeekerProfile;
import com.application.jobportal.repository.JobSeekerApplyRepository;

@Service
public class JobSeekerApplyService {

	private final JobSeekerApplyRepository jobSeekerApplyRepository;

	public JobSeekerApplyService(JobSeekerApplyRepository jobSeekerApplyRepository) {
		super();
		this.jobSeekerApplyRepository = jobSeekerApplyRepository;
	}
	
	public List<JobSeekerApply> getCandidateJobs(JobSeekerProfile userAccountId){
		return jobSeekerApplyRepository.findByUserId(userAccountId);
	}
	
	public List<JobSeekerApply> getJobCandidates(JobPostActivity job){
		return jobSeekerApplyRepository.findByJob(job);
	}

	public void addNew(JobSeekerApply jobSeekerApply) {
		jobSeekerApplyRepository.save(jobSeekerApply);
	}
	
}
