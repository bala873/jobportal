package com.application.jobportal.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="skills")
public class Skills {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String experienceLevel;
	private String yearOfExperience;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "job_seeker_profile")
	private JobSeekerProfile jobSeekerProfile;
	
	public Skills() {
		// TODO Auto-generated constructor stub
	}

	public Skills(int id, String name, String experienceLevel, String yearOfExperience,
			JobSeekerProfile jobSeekerProfile) {
		super();
		this.id = id;
		this.name = name;
		this.experienceLevel = experienceLevel;
		this.yearOfExperience = yearOfExperience;
		this.jobSeekerProfile = jobSeekerProfile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExperienceLevel() {
		return experienceLevel;
	}

	public void setExperienceLevel(String experienceLevel) {
		this.experienceLevel = experienceLevel;
	}

	public String getYearOfExperience() {
		return yearOfExperience;
	}

	public void setYearOfExperience(String yearOfExperience) {
		this.yearOfExperience = yearOfExperience;
	}

	public JobSeekerProfile getJobSeekerProfile() {
		return jobSeekerProfile;
	}

	public void setJobSeekerProfile(JobSeekerProfile jobSeekerProfile) {
		this.jobSeekerProfile = jobSeekerProfile;
	}

	@Override
	public String toString() {
		return "Skills [id=" + id + ", name=" + name + ", experienceLevel=" + experienceLevel + ", yearOfExperience="
				+ yearOfExperience + ", jobSeekerProfile=" + jobSeekerProfile + "]";
	}
	
	
	
}
