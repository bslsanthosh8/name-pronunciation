package com.wellsfargo.name.pronunciation.repository;

import com.wellsfargo.name.pronunciation.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, String> {
}

