package com.task2.EmployeeProject.Repositary;


import com.task2.EmployeeProject.Model.EmployeeEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>{
	List<EmployeeEntity> findByEmpSalaryGreaterThan(double salary);
}
