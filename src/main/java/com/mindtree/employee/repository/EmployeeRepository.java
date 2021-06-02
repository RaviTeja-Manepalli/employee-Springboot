package com.mindtree.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.employee.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>{


}
