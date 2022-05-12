package com.wellsfargo.name.pronunciation;

import java.util.ArrayList;
import java.util.List;

import com.wellsfargo.name.pronunciation.repository.EmployeeRepository;
import com.wellsfargo.name.pronunciation.repository.EmployeeService1;
import com.wellsfargo.name.pronunciation.repository.EnrolledApplicationRepository;
import com.wellsfargo.name.pronunciation.entity.Employee1;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    EmployeeService1 employeeService1;
    @MockBean
    EmployeeRepository employeeRepository;
    @MockBean
    EnrolledApplicationRepository enrolledApplicationRepository;

   // @Test
    public void testGetDepartment() throws Exception {
        Employee1 employee = new Employee1();
        employee.setId(1);

        List<Employee1> employeeList = new ArrayList<>();
        employeeList.add(employee);

        given(employeeService1.findAll()).willReturn(employeeList);

        this.mockMvc.perform(get("/employees")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1));
    }

}
