package com.micorservices.employee.controller;

import com.micorservices.employee.dto.APIResponseDto;
import com.micorservices.employee.dto.EmployeeDto;
import com.micorservices.employee.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employees")
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        return new ResponseEntity<>(employeeService.saveEmployee(employeeDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponseDto> getEmployeeById(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @GetMapping
    public ResponseEntity<EmployeeDto> getEmployeeById(@RequestParam String email){
        return ResponseEntity.ok(employeeService.getEmployeeByEmail(email));
    }
}
