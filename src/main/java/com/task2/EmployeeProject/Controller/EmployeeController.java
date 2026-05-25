package com.task2.EmployeeProject.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task2.EmployeeProject.Model.EmployeeEntity;
import com.task2.EmployeeProject.Service.EmployeeService;
import com.task2.EmployeeProject.VallidationAndExceptionHandle.EmployeeNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/EmployeeEntity")
public class EmployeeController {
	@Autowired
	private EmployeeService empService;
	
	/**
     * Retrieve all employee details with pagination and sorting.
     * 
     * @param page The page number (default is 0).
     * @param size The page size (default is 10).
     * @param sortBy The field to sort by (default is empId).
     * @param sortDir The sort direction (either 'asc' or 'desc', default is 'asc').
     * @return A paginated and sorted list of employees.
     */
	@Operation(
		 summary = "Get all employee details with pagination and sorting",
		 description = "Retrieve a paginated and sorted list of all employees in the system."
		    )
	@ApiResponses(value = {
		 @ApiResponse(responseCode = "200", description = "Successfully retrieved employee details"),
		 @ApiResponse(responseCode = "400", description = "Invalid request parameters")
		    })
	@GetMapping("/AllEmployees")
	public ResponseEntity<Page<EmployeeEntity>> GetAllEmployeeDetails( 
			@RequestParam(defaultValue = "0") int page, // Default page = 0
            @RequestParam(defaultValue = "3") int size, // Default page size = 10
            @RequestParam(defaultValue = "empId") String sortBy, // Default sort by empId
            @RequestParam(defaultValue = "asc") String sortDir // Default sort direction
            ) {
		// Determine the sorting direction
        Sort.Direction direction = Sort.Direction.fromString(sortDir.toUpperCase());

        // Create PageRequest with sorting and pagination
        PageRequest pageRequest = PageRequest.of(page, size, direction, sortBy);

        // Call the service method with the PageRequest
        Page<EmployeeEntity> employeePage = empService.getAllEmployees(pageRequest);
        return ResponseEntity.ok(employeePage);
	}
	
	/**
     * Retrieve an employee's details by their ID.
     * 
     * @param id The ID of the employee.
     * @return The employee details.
     * @throws EmployeeNotFoundException if the employee is not found.
     */
	@Operation(summary = "Get a Employee by ID", description = "Returns a single employee based on the given ID.")
	@ApiResponses(value = {
	     @ApiResponse(responseCode = "200", description = "Successfully retrieved employee"),
	     @ApiResponse(responseCode = "404", description = "employee not found")
	    })
	@GetMapping("/Employee/{id}")
	public ResponseEntity<Optional<EmployeeEntity>> GetEmployeeDetailsById(@Parameter(description = "ID of the employee to be fetched") @PathVariable long id) throws EmployeeNotFoundException {
		return ResponseEntity.ok(empService.getEmployeeById(id));
	}
	
	/**
     * Create a new employee in the system.
     * 
     * @param employee The employee data to create.
     * @return The created employee entity.
     */
	@Operation(summary = "Create a new employee", description = "Creates a new employee in the system.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Successfully created employee"),
	    @ApiResponse(responseCode = "400", description = "Invalid employee data")
	    })
	@PostMapping("/CreateEmployee")
	public ResponseEntity<EmployeeEntity> CreateEmployeeDetails(@RequestBody @Valid EmployeeEntity employee) {
		//System.out.println(employee);
		return new ResponseEntity<>(empService.createEmployee(employee),HttpStatus.CREATED);
	}
	
	 /**
     * Update an existing employee's details.
     * 
     * @param id The ID of the employee to update.
     * @param employee The updated employee entity.
     * @return The updated employee entity.
     * @throws EmployeeNotFoundException if the employee is not found.
     */
	@Operation(summary = "Update an existing employee", description = "Updates an existing employee in the system.")
	@ApiResponses(value = {
	     @ApiResponse(responseCode = "200", description = "Successfully updated employee"),
	     @ApiResponse(responseCode = "400", description = "Invalid employee data"),
	     @ApiResponse(responseCode = "404", description = "employee not found")
	    })
	@PutMapping("/UpdateEmployee/{id}")
	public EmployeeEntity UpdateEmployeeDetails(@Parameter(description = "ID of the employee to be updated") @PathVariable long id,@RequestBody @Valid EmployeeEntity employee) throws EmployeeNotFoundException {
		//System.out.println(employee);
		return empService.updateEmployee(id, employee);
	}
	
	/**
     * Delete an employee by their ID.
     * 
     * @param id The ID of the employee to delete.
     * @throws EmployeeNotFoundException if the employee is not found.
     */
	@Operation(summary = "Delete a employee by ID", description = "Deletes a employee based on the given ID.")
	@ApiResponses(value = {
	     @ApiResponse(responseCode = "200", description = "Successfully deleted employee"),
	     @ApiResponse(responseCode = "404", description = "employee not found")
	    })
	@DeleteMapping("/DeleteEmployee/{id}")
	public void DeleteEmployeeDetails(@Parameter(description = "ID of the employee to be deleted") @PathVariable long id) throws EmployeeNotFoundException {
		//System.out.println(id);
		empService.deleteEmployee(id);
		//return "deleted employee id:";
	}
	
	@GetMapping("/Employees/Salary")
	public List<EmployeeEntity> fetchGerterthanSalary(@RequestParam double Salary) {
		 return empService.fetchGerterthanSalary(Salary);
	}
}
