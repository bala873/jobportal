package com.application.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.jobportal.entity.JobPostActivity;
import com.application.jobportal.entity.JobSeekerProfile;
import com.application.jobportal.entity.JobSeekerSave;

@Repository
public interface JobSeekerSaveRepository extends JpaRepository<JobSeekerSave, Integer>{

	List<JobSeekerSave> findByUserId(JobSeekerProfile userAccountId);
	List<JobSeekerSave> findByJob(JobPostActivity job);
	
}