package com.wellsfargo.name.pronunciation.repository;

import com.wellsfargo.name.pronunciation.entity.EnrolledApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrolledApplicationRepository extends JpaRepository<EnrolledApplication, String> {
}

