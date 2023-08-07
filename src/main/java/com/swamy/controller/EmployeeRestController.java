package com.swamy.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.swamy.dto.EmployeeDto;
import com.swamy.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeRestController.class);

	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {

		EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
		return savedEmployee;
	}

	@GetMapping
	public ResponseEntity<List<EmployeeDto>> getAllEmployees() {

		List<EmployeeDto> employees = employeeService.getAllEmployees();
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Integer employeeId) {

		EmployeeDto employee = employeeService.getEmployeeById(employeeId);
		return ResponseEntity.ok(employee);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Integer employeeId,
			@RequestBody EmployeeDto employeeDto) {

		EmployeeDto updatedEmployee = employeeService.updateEmployee(employeeId, employeeDto);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") Integer employeeId) {

		String deletedEmployee = employeeService.deleteEmployee(employeeId);
		return ResponseEntity.ok(deletedEmployee);
	}
}
