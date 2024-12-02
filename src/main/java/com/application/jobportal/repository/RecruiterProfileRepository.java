package com.application.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.jobportal.entity.RecruiterProfile;

public interface RecruiterProfileRepository extends JpaRepository<RecruiterProfile, Integer> {
}