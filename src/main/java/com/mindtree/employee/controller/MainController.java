package com.mindtree.employee.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.employee.model.Employee;
import com.mindtree.employee.service.ServiceImpl;
import com.mindtree.exceptions.EmployeeServiceException;
import com.mindtree.exceptions.NoEmployeeFoundException;

@RestController
@RequestMapping(path="/employee")

public class MainController {
	@Autowired
	 ServiceImpl service;	
	
	@PostMapping("/add")
	public ResponseEntity<?> createEmployee(@RequestBody Employee employ) {
		Employee emp = null;
		try {
			emp = service.addEmployee(employ);
			return new ResponseEntity<>("Added employee",HttpStatus.ACCEPTED);
		} catch (EmployeeServiceException e) {
			return new ResponseEntity<>("Failed to create",HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping(path="/all")
	public List<Employee> getAllEmployees(){
		
		return service.getAllEmpData();
	
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") int id,Employee e1)  
	{  //List<Employee> employ = new ArrayList<Employee>();
	    try {
			service.deleteonId(id,e1);
		} catch (EmployeeServiceException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>("Failed to delete",HttpStatus.BAD_REQUEST);

		}  
	   /* if(res==false)
	    {
	    	System.out.println("failed to delete");

	    }*/
	    return new ResponseEntity<>("Delete success",HttpStatus.ACCEPTED);	    
	   
	}  

	
	@PutMapping("/update/{id}")
	public ResponseEntity<?>  updateEmployee(@RequestBody Employee newEmployee,@PathVariable Integer id)  {
		Employee emp =null;
		try {
			emp = service.updateEmployeeDetails(newEmployee, id);
		    return new ResponseEntity<Employee>(emp,HttpStatus.ACCEPTED);	    

		} catch (NoEmployeeFoundException e) {
			// TODO Auto-generated catch block
		    return new ResponseEntity<>("Failed no employee found",HttpStatus.BAD_REQUEST);	    

			//throw new NoEmployeeFoundException("No id is present");
		}
		
	}
	
	
}
