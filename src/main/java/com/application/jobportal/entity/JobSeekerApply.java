package com.application.jobportal.entity;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class JobSeekerApply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date applyDate;
	
	private String coverLetter;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId" , referencedColumnName = "user_account_id")
	private JobSeekerProfile userId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "job",referencedColumnName = "jobPostId")
	private JobPostActivity job;
	
	public JobSeekerApply() {
		// TODO Auto-generated constructor stub
	}

	public JobSeekerApply(Integer id, Date applyDate, String coverLetter, JobSeekerProfile userId,
			JobPostActivity job) {
		super();
		this.id = id;
		this.applyDate = applyDate;
		this.coverLetter = coverLetter;
		this.userId = userId;
		this.job = job;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getCoverLetter() {
		return coverLetter;
	}

	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}

	public JobSeekerProfile getUserId() {
		return userId;
	}

	public void setUserId(JobSeekerProfile userId) {
		this.userId = userId;
	}

	public JobPostActivity getJob() {
		return job;
	}

	public void setJob(JobPostActivity job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return "JobSeekerApply [id=" + id + ", applyDate=" + applyDate + ", coverLetter=" + coverLetter + ", userId="
				+ userId + ", job=" + job + "]";
	}
	
	
}
