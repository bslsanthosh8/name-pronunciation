package com.wellsfargo.name.pronunciation.repository;

import com.wellsfargo.name.pronunciation.entity.Entitlement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntitlementRepository extends JpaRepository<Entitlement, String> {
}
