package com.wellsfargo.name.pronunciation.service.impl;

import com.wellsfargo.name.pronunciation.entity.Employee;
import com.wellsfargo.name.pronunciation.exception.NamePronunciationException;
import com.wellsfargo.name.pronunciation.repository.EmployeeRepository;
import com.wellsfargo.name.pronunciation.service.EmployeeService;
import com.wellsfargo.name.pronunciation.utils.ErrorType;
import com.wellsfargo.name.pronunciation.utils.Messages;
import com.wellsfargo.name.pronunciation.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    Messages messages;

    @Override
    public List<Employee> getEmployees(String searchCriteria) {
        return employeeRepository.findEmployeeByCriteria(searchCriteria);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee createOrUpdateEmployee(Employee employee) throws NamePronunciationException {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployee(String uid) throws NamePronunciationException {
        if (uid == null)
            throw new NamePronunciationException(Status.FAILED.name(), ErrorType.SYSTEM.name(), messages.get("np-msg.NPA002"));
        return employeeRepository.findById(uid).orElseThrow(() -> new NamePronunciationException(Status.FAILED.name(), ErrorType.SYSTEM.name(), messages.get("np-msg.NPA002")));
    }

    @Override
    public void deleteEmployee(String uid) throws NamePronunciationException {
        Employee employee = employeeRepository.getById(uid);
        if (employee != null && (!employee.getEntitlement().equals("ADMIN"))) {
            throw new NamePronunciationException(Status.FAILED.name(), ErrorType.SERVICE.name(), messages.get("np-mwg-NPA006"));
        }
        employeeRepository.deleteById(uid);
    }
}
