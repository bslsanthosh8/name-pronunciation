

package com.wellsfargo.name.pronunciation.controller;

import com.wellsfargo.name.pronunciation.entity.AuditLog;
import com.wellsfargo.name.pronunciation.entity.Employee;
import com.wellsfargo.name.pronunciation.entity.Employee1;
import com.wellsfargo.name.pronunciation.entity.EnrolledApplication;
import com.wellsfargo.name.pronunciation.entity.NamePronunciation;
import com.wellsfargo.name.pronunciation.exception.NamePronunciationException;
import com.wellsfargo.name.pronunciation.repository.AuditLogRepository;
import com.wellsfargo.name.pronunciation.repository.EmployeeRepository;
import com.wellsfargo.name.pronunciation.repository.EmployeeService1;
import com.wellsfargo.name.pronunciation.repository.EnrolledApplicationRepository;
import com.wellsfargo.name.pronunciation.repository.NamePronunciationRepository;
import com.wellsfargo.name.pronunciation.model.response.NamePronunciationResponse;
import com.wellsfargo.name.pronunciation.utils.Messages;
import com.wellsfargo.name.pronunciation.utils.Status;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
public class TestController {

    @Autowired
    private EmployeeService1 employeeService1;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EnrolledApplicationRepository enrolledApplicationRepository;

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Autowired
    private NamePronunciationRepository namePronunciationRepository;

    @Autowired
    Messages messages;

    // Select, Insert, Delete, Update Operations for an Employee

    @RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
    Employee1 getEmployee(@PathVariable Integer id) throws IOException {
        String msg = messages.get("np-msg.NPA001");
        System.out.println("######## message test" + msg);
        Employee emp = new Employee();
        emp.setEmpId("1901914");
        emp.setUid("u752502");
        emp.setEntitlement("ADMIN");
        emp.setFirstName("Ravi");
        emp.setLastName("katkar");
        emp.setMiddleName("");
        emp.setCreatedBy("SYSTEM");
        emp.setCreatedTimestamp(new Date());
        emp.setModifiedTimestamp(new Date());
        employeeRepository.save(emp);

        EnrolledApplication enrolledApplication = new EnrolledApplication();
        enrolledApplication.setAppDesc("sampleApp");
        enrolledApplication.setAppToken(UUID.randomUUID().toString());
        enrolledApplication.setCreatedBy("SYSTEM");
        enrolledApplication.setCreatedTimestamp(new Date());
        enrolledApplication.setModifiedTimestamp(new Date());
        enrolledApplicationRepository.save(enrolledApplication);

        AuditLog auditLog = new AuditLog();
        auditLog.setAppId(1);
        auditLog.setRequestedName("ravi");
        auditLog.setUid("u752502");
        auditLog.setCreatedTimestamp(new Date());
        auditLog.setServiceName("myName");
        auditLogRepository.save(auditLog);

        NamePronunciation namePronunciation = new NamePronunciation();
        byte[] sound = FileUtils.readFileToByteArray(new File("C:\\Users\\ravi katkar\\IdeaProjects\\spring-boot-data-H2-embedded\\src\\main\\resources\\sampleVoice.mp3"));
        namePronunciation.setPronunciationSound(sound);
        namePronunciation.setFormat("MP3");
        namePronunciation.setUid("u752502");
        namePronunciation.setCreatedBy("SYSTEM");
        // namePronunciation.setCreatedTimestamp(new Date());
        // namePronunciation.setModifiedTimestamp(new Date());
        namePronunciationRepository.save(namePronunciation);
        return employeeService1.findById(id).get();
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    String addEmployee(@RequestBody Employee1 employee) {

        return "SUCCESS";
    }

    @RequestMapping(value = "/test", method = RequestMethod.PUT)
    Employee1 updateEmployee(@RequestBody Employee1 employee) {
        return employeeService1.save(employee);
    }

    @RequestMapping(value = "/test", method = RequestMethod.DELETE)
    Map<String, String> deleteEmployee(@RequestParam Integer id) {
        Map<String, String> status = new HashMap<>();
        Optional<Employee1> employee = employeeService1.findById(id);
        if (employee.isPresent()) {
            employeeService1.delete(employee.get());
            status.put("Status", "Employee deleted successfully");
        } else {
            status.put("Status", "Employee not exist");
        }
        return status;
    }

    // Select, Insert, Delete for List of Employees

    @RequestMapping(value = "/tests", method = RequestMethod.GET)
    List<Employee1> getAllEmployee() {
        List<Employee> employees = employeeRepository.findEmployeeByCriteria("u7%");
        System.out.println(" $$$$$$$$$$$$$$$$$$$$$$$$$");
        employees.forEach(employee -> System.out.println(" " + employee.getUid()));
        System.out.println(" $$$$$$$$$$$$$$$$$$$$$$$$$");


        return employeeService1.findAll();
    }

    @RequestMapping(value = "/tests", method = RequestMethod.POST)
    String addAllEmployees(@RequestBody List<Employee1> employeeList) {
        employeeService1.saveAll(employeeList);
        return "SUCCESS";
    }


    @RequestMapping(value = "/tests", method = RequestMethod.DELETE)
    String addAllEmployees() {
        employeeService1.deleteAll();
        return "SUCCESS";
    }

    static int i;

    @RequestMapping(value = "/testCB")
    @CircuitBreaker(name = "testCircuitBreaker", fallbackMethod = "fallbackMethodTest")
    public ResponseEntity<NamePronunciationResponse<String>> testCircuit() throws Exception {

        System.out.println("Inside @@@@@@@@@@@@@@@@@@@");
        i++;
        if (i > 3) {

            throw new NamePronunciationException(Status.FAILED.name(), "SYSTEM", "testCB");
        }
        NamePronunciationResponse response = NamePronunciationResponse.builder().status(Status.FAILED.name()).message("testCB").build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ResponseEntity<NamePronunciationResponse<String>> fallbackMethodTest(Exception e) {
        System.out.println("Inside the fallback method #################");
        String msg = "";
        NamePronunciationResponse response = NamePronunciationResponse.builder().status(Status.FAILED.name()).message(msg).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
