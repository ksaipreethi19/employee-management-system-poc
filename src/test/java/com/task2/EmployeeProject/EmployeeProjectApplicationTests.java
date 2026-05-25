/*package com.task2.EmpoyeeProject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.task2.EmployeeProject.EmployeeProjectApplication;
import com.task2.EmployeeProject.Model.EmployeeEntity;
import com.task2.EmployeeProject.Repositary.EmployeeRepository;
import com.task2.EmployeeProject.Service.EmployeeService;
import com.task2.EmployeeProject.VallidationAndExceptionHandle.EmployeeNotFoundException;

@SpringBootTest(classes = EmployeeProjectApplication.class)
public class EmployeeProjectApplicationTests {
	
	 /*@Test
	    void contextLoads() {
	    }*/
	 
	/* @Autowired
	    private EmployeeRepository empRepository; // Mock the repository

	    @Autowired
	    private EmployeeService empService; // Inject the mock repository into the service

	    private EmployeeEntity employee;

	    @BeforeEach
	    public void setup() {
	        employee = new EmployeeEntity();
	        //employee.setEmpId(1L);
	        employee.setFirstName("Saipreethi");
	        employee.setLastName("Kothapelli");
			employee.setEmail("Kothapelli.saipreethi@capgemini.com");
			employee.setDepartment("computer science and engineering");
			employee.setJobTitle("full stack java developer");
	        employee.setEmpSalary(40000);
	        empRepository.save(employee);
	        System.out.println(employee);
	    }

	    @Test
	    public void testCreateEmployee() {
	    	EmployeeEntity createdEmployee = empService.createEmployee(employee);

	        // Assert: Verify the employee was created successfully
	        assertNotNull(createdEmployee);
	        assertEquals("Saipreethi", createdEmployee.getFirstName());
	        assertEquals("Java Developer", createdEmployee.getJobTitle());
	        assertEquals(40000, createdEmployee.getEmpSalary());
	        System.out.println("created details:"+employee);
	    }

	   @Test
	    public void testGetEmployeeById(){
	        // Act
		   Optional<EmployeeEntity> foundEmployee = empRepository.findById(employee.getEmpId());

	        // Assert
	        assertTrue(foundEmployee.isPresent());
	        assertEquals("Saipreethi", foundEmployee.get().getFirstName());
	        System.out.println("Search By Id:"+foundEmployee);
	    }

	    @Test
	    public void testUpdateEmployee() throws EmployeeNotFoundException {
	    	
	    	Optional<EmployeeEntity> foundEmployee = empRepository.findById(employee.getEmpId());
	        // Arrange
	        //EmployeeEntity updatedEmployee = new EmployeeEntity();
	    	assertTrue(foundEmployee.isPresent());
	    	// Modify the employee details
	        EmployeeEntity employeeToUpdate = foundEmployee.get();
	        employeeToUpdate.setFirstName("Saikeerthana");
	        employeeToUpdate.setJobTitle("Senior Java Developer");
	        employeeToUpdate.setEmpSalary(45000);

	        // Act
	        EmployeeEntity result = empService.updateEmployee(employee.getEmpId(), employeeToUpdate);

	        // Assert
	        assertNotNull(result);
	        assertEquals("Saikeerthana", result.getFirstName());
	        assertEquals("Senior Java Developer", result.getJobTitle());
	        assertEquals(45000, result.getEmpSalary());
	        System.out.println("update employee details"+employeeToUpdate);
	    }

	    @Test
	    public void testDeleteEmployee() throws EmployeeNotFoundException {
	    	Optional<EmployeeEntity> foundEmployee = empRepository.findById(employee.getEmpId());
	        assertTrue(foundEmployee.isPresent());

	        // Act: Delete the employee
	        empService.deleteEmployee(employee.getEmpId());

	        // Assert: Verify the employee no longer exists in the repository
	        Optional<EmployeeEntity> deletedEmployee = empRepository.findById(employee.getEmpId());
	        assertTrue(deletedEmployee.isEmpty());
	        System.out.println("Details about delete employees"+deletedEmployee);
	    }
	
}*/
