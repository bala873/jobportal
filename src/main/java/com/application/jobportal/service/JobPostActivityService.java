package com.application.jobportal.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.application.jobportal.entity.IRecruiterJobs;
import com.application.jobportal.entity.JobCompany;
import com.application.jobportal.entity.JobLocation;
import com.application.jobportal.entity.JobPostActivity;
import com.application.jobportal.entity.RecruiterJobsDto;
import com.application.jobportal.repository.JobPostActivityRepository;

@Service
public class JobPostActivityService {
	
	private final JobPostActivityRepository jobPostActivityRepository;
	
		

	public JobPostActivityService(JobPostActivityRepository jobPostActivityRepository) {
		super();
		this.jobPostActivityRepository = jobPostActivityRepository;
	}



	public JobPostActivity addNew(JobPostActivity jobPostActivity) {
		return jobPostActivityRepository.save(jobPostActivity);
	}
	
	public List<RecruiterJobsDto> getRecruiterJobs(int recruiter){
		
		List<IRecruiterJobs> recruiterJobsDtos = jobPostActivityRepository.getRecruiterJobs(recruiter);
		
		List<RecruiterJobsDto> recruiterJobsDtosList = new ArrayList<>();
		
		for(IRecruiterJobs rec : recruiterJobsDtos) {
			JobLocation loc = new JobLocation(rec.getLocationId(),rec.getCity(),rec.getState(),rec.getCountry());
			JobCompany comp = new  JobCompany(rec.getCompanyId(),rec.getName(),"");
			recruiterJobsDtosList.add(new RecruiterJobsDto(rec.getTotalCandidates(), rec.getJob_post_id(), rec.getJob_title(), loc, comp));
		}
		
		
		
		return recruiterJobsDtosList;
	}



	public JobPostActivity getOne(int id) {
		
		return jobPostActivityRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found "));
	}



	public List<JobPostActivity> getAll() {
		
		return jobPostActivityRepository.findAll();
	}



	public List<JobPostActivity> search(String job, String location, List<String> type, List<String> remote,
			LocalDate searchDate) {
		
		return Objects.isNull(searchDate)?jobPostActivityRepository.searchWithoutDate(job,location,type,remote) :jobPostActivityRepository.search(job, location, type, remote, searchDate);
		
	}
}
