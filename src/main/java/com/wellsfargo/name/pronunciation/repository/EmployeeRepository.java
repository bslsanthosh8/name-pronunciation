package com.wellsfargo.name.pronunciation.repository;

import com.wellsfargo.name.pronunciation.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Query(value = "SELECT * FROM EMPLOYEE where UID like ?1 or FIRST_NAME like ?1 or LAST_NAME like ?1 or MIDDLE_NAME like ?1 or PREFERRED_NAME like ?1",
            nativeQuery = true)
    List<Employee> findEmployeeByCriteria(String criteria);
}
