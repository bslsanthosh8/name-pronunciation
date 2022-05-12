package com.wellsfargo.name.pronunciation;

import com.wellsfargo.name.pronunciation.entity.Employee1;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

//@SpringBootTest(webEnvironment = RANDOM_PORT)
public class EmployeeControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

   // @Test
    public void testEmployee()
    {
        Assertions.assertTrue(
                this.restTemplate
                        .getForObject("http://localhost:" + port + "/employee/1", Employee1.class)
                        .getId().equals(1));
    }

   // @Test
    public void testAllEmployee() {
        assertTrue(
                this.restTemplate
                        .getForObject("http://localhost:" + port + "/employees", List.class).size() == 2);
    }
}

