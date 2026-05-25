/*&package com.task2.EmployeeProject;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.task2.EmployeeProject.Model.EmployeeEntity;
import com.task2.EmployeeProject.Service.EmployeeService;

@Component
public class EmployeConfiguration implements CommandLineRunner {
	@Autowired
	private EmployeeService empService;

	@Override
	public void run(String... args) throws Exception {
		 // Use Scanner to prompt user for input
        Scanner scanner = new Scanner(System.in);

        // Prompt user for the number of employees to add
        System.out.print("Numbers of employees Are: ");
        int numEmployees = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character left by nextInt()

        // Loop to add multiple employees
        for (int i = 0; i < numEmployees; i++) {
            System.out.println("Enter details for employee " + (i + 1));

            // Prompt user for employee details
            System.out.print("Enter employee name: ");
            String name = scanner.nextLine();

            System.out.print("Enter employee role: ");
            String role = scanner.nextLine();

            System.out.print("Enter employee salary: ");
            double salary = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character left by nextDouble()

            // Create and save the employee
            EmployeeEntity savedEmployee = empService.createEmployee(name, role, salary);
            System.out.println("Employee " + savedEmployee.getEmpName() + " saved successfully!");
        }
           //fetching All Employees
        
            List<EmployeeEntity> AllEmployees=empService.getAllEmployees();
            System.out.println("Storing Employee Details"+AllEmployees);
            
            //Searching the employee Details By Using id
            System.out.print("Enter employee ID to search: ");
            long searchId = scanner.nextLong();
            scanner.nextLine(); 
            Optional<EmployeeEntity> empById=empService.getEmployeeById(searchId);
            if (empById.isPresent()) {
                System.out.println("Employee Details: " + empById.get());
            } else {
                System.out.println("No employee found with ID " + searchId);
            }
            
            //update Employee Details
            
            System.out.print("Enter employee ID to Update: ");
            long updateId=scanner.nextLong();
            scanner.nextLine(); 
            System.out.print("Enter Update employee name: ");
            String updateName = scanner.nextLine();

            System.out.print("Enter Update employee role: ");
            String updateRole = scanner.nextLine();

            System.out.print("Enter Update employee salary: ");
            double updateSalary = scanner.nextDouble();
            scanner.nextLine();
            //String Uname=scanner.next();
            EmployeeEntity updateEmployee=empService.updateEmployee(updateId,updateName, updateRole, updateSalary);
            //Passing the Arugementes in updateEmployeeMethod 
            System.out.println("Update Employee Id"+updateId+"Updated Employee Details"+updateEmployee);
           
            //delete employee details by using empId
            System.out.print("Enter employee ID to Delete: ");
            long deleteId=scanner.nextLong();
            empService.deleteEmployee(deleteId);
	}

}*/
