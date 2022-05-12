package com.wellsfargo.name.pronunciation.service;

import com.wellsfargo.name.pronunciation.entity.Employee;
import com.wellsfargo.name.pronunciation.exception.NamePronunciationException;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees(String searchCriteria);

    List<Employee> getAllEmployees();

    Employee createOrUpdateEmployee(Employee employee) throws NamePronunciationException;

    Employee getEmployee(String uid) throws NamePronunciationException;

    void deleteEmployee(String uid) throws NamePronunciationException;


}
