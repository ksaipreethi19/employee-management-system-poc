package com.task2.EmployeeProject.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.task2.EmployeeProject.Model.EmployeeEntity;
import com.task2.EmployeeProject.Repositary.EmployeeRepository;
import com.task2.EmployeeProject.VallidationAndExceptionHandle.EmployeeNotFoundException;

@Service
public class EmployeeService implements EmployeeServiceInterface {
	
	@Autowired
	private EmployeeRepository empRepository;
	
	//Using This Method is Controller to GetAllEmployeeDetails
			@Override
			public Page<EmployeeEntity> getAllEmployees(Pageable pageable) {
				return empRepository.findAll(pageable);
				}
							
			//Using This Method is Controller return the employee details use by empid
			@Override
			public Optional<EmployeeEntity> getEmployeeById(long id) throws EmployeeNotFoundException {
				Optional<EmployeeEntity> employee = empRepository.findById(id);
		        if (employee.isEmpty()) {
		            throw new EmployeeNotFoundException("Employee Not found with ID: " + id);
		        }
		        return employee;
				}
			
			//Using This Method is Controller save the employee details
			@Override
			public EmployeeEntity createEmployee(EmployeeEntity empEntity) {
				 return empRepository.save(empEntity);
			}
			
			//Using This Method is Controller Update the employee details
			@Override
			public EmployeeEntity updateEmployee(long id, EmployeeEntity employee) throws EmployeeNotFoundException {

				EmployeeEntity existing = empRepository.findById(id)
						.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));

				existing.setFirstName(employee.getFirstName());
				existing.setLastName(employee.getLastName());
				existing.setEmail(employee.getEmail());
				existing.setDepartment(employee.getDepartment());
				existing.setJobTitle(employee.getJobTitle());
				existing.setEmpSalary(employee.getEmpSalary());

				return empRepository.save(existing);

			}
			
			//Using This Method DeleteEmployee Details by use Empid
			@Override
			public void deleteEmployee(Long id) throws EmployeeNotFoundException {
				if(!empRepository.existsById(id)) {
					 throw new EmployeeNotFoundException("Employee not found with ID: " + id);
				}
				empRepository.deleteById(id);
			}
			public List<EmployeeEntity> fetchGerterthanSalary(double salary) {
				return empRepository.findByEmpSalaryGreaterThan(salary);
			}
  }
	




















/*//Using this method to save the employee details in repository
	 public EmployeeEntity createEmployee(String name, String role, double salary) {
		 if((name == null || name.isEmpty())) {
			 throw new IllegalArgumentException("Employee name cannot be null or empty");
		   }
		 if((role == null || role.isEmpty())) {
			 throw new IllegalArgumentException("Employee Salary cannot be null or empty");
		 }
		 if(salary<=0) {
			 throw new IllegalArgumentException("Salary must be Greater than Zero");
		 }
		 
		   //passing the Arguments of EntityModel
	        EmployeeEntity employee = new EmployeeEntity(name, role, salary);
	        
	        return empRepository.save(employee);
	    }
	 
	 	//Using This Method is GetAllEmployeeDetails
		public List<EmployeeEntity> getAllEmployees() {
			return empRepository.findAll();
		}
		
		//Using This Method return the employee details use by empid
		public Optional<EmployeeEntity>  getEmployeeById(long id) {
			return empRepository.findById(id);
		}
		
		//Using This Method Update the Employee details
		public EmployeeEntity updateEmployee(Long id, String name, String role, double salary) {
			if((name == null || name.isEmpty())) {
				 throw new IllegalArgumentException("Employee name cannot be null or empty");
			   }
			 if((role == null || role.isEmpty())) {
				 throw new IllegalArgumentException("Employee Salary cannot be null or empty");
			 }
			 if(salary<=0) {
				 throw new IllegalArgumentException("Salary must be Greater than Zero");
			 }
			// Find employee by ID
	        Optional<EmployeeEntity> optionalEmployee = empRepository.findById(id);
	        
	        if (optionalEmployee.isPresent()) {
	            EmployeeEntity employee = optionalEmployee.get();
	            employee.setEmpName(name);
	            employee.setEmpRole(role);
	            employee.setEmpSalary(salary);
	            
	            // Save updated employee
	            return empRepository.save(employee);
	        } else {
	            // Handle employee not found scenario
	            throw new RuntimeException("Employee not found with ID: " + id);
	        }
	    }
		
		//Using This Method DeleteEmployee Details by use Empid
		public void deleteEmployee(Long id) {
	        // Check if the employee exists before attempting to delete
	        Optional<EmployeeEntity> employee = empRepository.findById(id);
	        if (employee.isPresent()) {
	            empRepository.deleteById(id); // Delete the employee by ID
	            System.out.println("Employee with ID " + id + " deleted successfully.");
	        } else {
	            throw new RuntimeException("Employee not found with ID: " + id);
	        }
		}
}*/
