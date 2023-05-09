package com.micorservices.employee.service;

import com.micorservices.employee.dto.APIResponseDto;
import com.micorservices.employee.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    APIResponseDto getEmployeeById(Long id);
    EmployeeDto getEmployeeByEmail(String email);
}
