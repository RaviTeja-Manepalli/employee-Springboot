package com.mindtree.employee.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.employee.model.Employee;
import com.mindtree.employee.repository.EmployeeRepository;
import com.mindtree.exceptions.EmployeeServiceException;
import com.mindtree.exceptions.NoEmployeeFoundException;

@Service
public class ServiceImpl  {
	
	@Autowired
	private EmployeeRepository employee;

	public Employee addEmployee(Employee emp) throws EmployeeServiceException {
		// TODO Auto-generated method stub
		Employee employ;
		try {
			employ = employee.save(emp);
		} catch (Exception e) {
			throw new EmployeeServiceException(e);
		}

		return employ;
	}

	public Employee getEmployeeById(int Id) throws NoEmployeeFoundException {
		// TODO Auto-generated method stub
		Employee emp=employee.findById(Id).orElse(null);
		if(emp==null)
			throw new NoEmployeeFoundException("no employee found");
		return emp;
	}

	public List<Employee> getAllEmpData() {
		// TODO Auto-generated method stub
		List<Employee> emp = new ArrayList<Employee>();
		emp = employee.findAll();
		return emp;

	}

	public Employee deleteonId(int id,Employee e1) throws EmployeeServiceException {
		// TODO Auto-generated method stub
		employee.deleteById(id);
		return e1;
		/*List<Employee> employ = new ArrayList<Employee>();
		employ = employee.findAll();*/

	}

	public Employee updateEmployeeDetails(Employee newEmployee, int id) throws NoEmployeeFoundException {
		// TODO Auto-generated method stub
		try {
		Employee emp = employee.findById(id).orElse(null);
		emp.setAddress(newEmployee.getAddress());
		emp.setMobileNumber(newEmployee.getMobileNumber());
		emp.setName(newEmployee.getName());
		employee.save(emp);
		if(emp==null)
			throw new NoEmployeeFoundException("No Employee to update");
		return emp;

		}
		catch(Exception e)
		{
			throw new NoEmployeeFoundException("No Employee to update");
		}
		
		
	}

}
