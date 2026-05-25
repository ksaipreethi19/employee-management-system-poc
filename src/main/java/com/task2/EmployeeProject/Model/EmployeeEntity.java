package com.task2.EmployeeProject.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Employee")
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class EmployeeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//Auto Generated Id
	private long empId;

	@NotBlank(message = "fname cannot be blank")
	@Size(min = 2, max = 100, message = "Employee name must be between 2 and 100 characters")
	private String firstName;
	@NotBlank(message = "lname cannot be blank")
	@Size(min = 2, max = 100, message = "Employee name must be between 2 and 100 characters")
	private String lastName;
	@Email(message = "Email must be valid")
	private String email;
	private String department;
	//	@NotBlank(message = "Jobtitle cannot be blank")
//	//@Size(min = 2, max = 50, message = "Employee role must be between 2 and 50 characters")
	private String jobTitle;
	@Positive(message="salary must be positive")
	private double empSalary;

}


	/*//Default Constructor
	public EmployeeEntity() { }
	
	//Parameterized Constructor
	public EmployeeEntity(String empName, String empRole, double empSalary) {
		this.empName = empName;
		this.empRole = empRole;
		this.empSalary = empSalary;
	}
	//Generate Getters and Setters
	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long id) {
		this.empId = id;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpRole() {
		return empRole;
	}
	public void setEmpRole(String empRole) {
		this.empRole = empRole;
	}
	public double getEmpSalary() {
		return empSalary;
	}
	public void setEmpSalary(double empSalary) {
		this.empSalary = empSalary;
	}
	@Override
	public String toString() {
		return "EmployeeEntity [empId=" + empId + ", empName=" + empName + ", empRole=" + empRole + ", empSalary="
				+ empSalary + "]";
	}*/
