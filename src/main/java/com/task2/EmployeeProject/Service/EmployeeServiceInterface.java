package com.task2.EmployeeProject.Service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.task2.EmployeeProject.Model.EmployeeEntity;
import com.task2.EmployeeProject.VallidationAndExceptionHandle.EmployeeNotFoundException;

public interface EmployeeServiceInterface {
	public EmployeeEntity createEmployee(EmployeeEntity empEntity);
	public Optional<EmployeeEntity>  getEmployeeById(long id) throws EmployeeNotFoundException;
	public EmployeeEntity updateEmployee(long id,EmployeeEntity empEntity) throws EmployeeNotFoundException;
	public void deleteEmployee(Long id) throws EmployeeNotFoundException;
	Page<EmployeeEntity> getAllEmployees(Pageable pageable);

}
