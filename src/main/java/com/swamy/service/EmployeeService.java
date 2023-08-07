package com.swamy.service;

import java.util.List;

import com.swamy.dto.EmployeeDto;

public interface EmployeeService {

	EmployeeDto saveEmployee(EmployeeDto employeeDto);

	List<EmployeeDto> getAllEmployees();

	EmployeeDto getEmployeeById(Integer employeeId);

	EmployeeDto updateEmployee(Integer employeeId, EmployeeDto employeeDto);

	String deleteEmployee(Integer employeeId);
}
