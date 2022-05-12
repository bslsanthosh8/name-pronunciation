package com.wellsfargo.name.pronunciation.repository;

import com.wellsfargo.name.pronunciation.entity.EmployeeAvatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeAvatarRepository extends JpaRepository<EmployeeAvatar, String> {
}
