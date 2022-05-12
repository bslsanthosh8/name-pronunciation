package com.wellsfargo.name.pronunciation.controller;

import com.wellsfargo.name.pronunciation.entity.Employee;
import com.wellsfargo.name.pronunciation.exception.NamePronunciationException;
import com.wellsfargo.name.pronunciation.model.request.EmployeeSearchRequest;
import com.wellsfargo.name.pronunciation.model.response.NamePronunciationResponse;
import com.wellsfargo.name.pronunciation.service.EmployeeService;
import com.wellsfargo.name.pronunciation.utils.Messages;
import com.wellsfargo.name.pronunciation.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    Messages messages;

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public ResponseEntity<NamePronunciationResponse<List<Employee>>> getEmployeesGivenSearchCriteria(EmployeeSearchRequest searchCriteria) {

        List<Employee> employeeList = employeeService.getEmployees(searchCriteria.getSearchCriteria());
        if (employeeList.isEmpty()) {
            return new ResponseEntity<>(NamePronunciationResponse.<List<Employee>>builder().data(new ArrayList<>()).status(Status.FAILED.name()).message(messages.get("np-msg.NPA004")).build(), HttpStatus.OK);
        }
        if (employeeList.size() > 100) {
            return new ResponseEntity<>(NamePronunciationResponse.<List<Employee>>builder().data(new ArrayList<>()).status(Status.FAILED.name()).message(messages.get("np-msg.NPA005")).build(), HttpStatus.OK);

        }
        return new ResponseEntity<>(NamePronunciationResponse.<List<Employee>>builder().data(employeeList).status(Status.SUCCESS.name()).build(), HttpStatus.OK);

    }

    @RequestMapping(value = "/employee/{uid}", method = RequestMethod.GET)
    public ResponseEntity<NamePronunciationResponse<Employee>> getEmployee(@PathVariable("uid") String uid) {
        try {
            Employee employee = employeeService.getEmployee(uid);
            return new ResponseEntity<>(NamePronunciationResponse.<Employee>builder().data(employee).status(Status.SUCCESS.name()).build(), HttpStatus.OK);

        } catch (NamePronunciationException exception) {
            return new ResponseEntity<>(NamePronunciationResponse.<Employee>builder().status(Status.SUCCESS.name()).build(), HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/employee/{uid}", method = RequestMethod.DELETE)
    public ResponseEntity<NamePronunciationResponse<Employee>> deleteEmployee(@PathVariable String uid) {
        try {
            employeeService.deleteEmployee(uid);
            return new ResponseEntity<>(NamePronunciationResponse.<Employee>builder().status(Status.SUCCESS.name()).build(), HttpStatus.OK);

        } catch (NamePronunciationException exception) {
            return new ResponseEntity<>(NamePronunciationResponse.<Employee>builder().status(Status.FAILED.name()).message(exception.getMessage()).build(), HttpStatus.OK);
        }

    }

    //TODO u752502 need to check how to handle the update?
    @RequestMapping(value = "/employee", method = RequestMethod.PUT)
    public ResponseEntity<NamePronunciationResponse<Employee>> deleteEmployee(@RequestBody Employee emp) {
        Employee employee;
        try {
            employee = employeeService.createOrUpdateEmployee(emp);
            return new ResponseEntity<>(NamePronunciationResponse.<Employee>builder().status(Status.SUCCESS.name()).data(employee).build(), HttpStatus.OK);

        } catch (NamePronunciationException exception) {
            return new ResponseEntity<>(NamePronunciationResponse.<Employee>builder().status(Status.FAILED.name()).message(exception.getMessage()).build(), HttpStatus.OK);
        }

    }

}
