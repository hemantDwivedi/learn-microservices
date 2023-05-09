package com.micorservices.employee.service.impl;

import com.micorservices.employee.dto.APIResponseDto;
import com.micorservices.employee.dto.DepartmentDto;
import com.micorservices.employee.dto.EmployeeDto;
import com.micorservices.employee.entity.Employee;
import com.micorservices.employee.exception.EmailAlreadyExistsException;
import com.micorservices.employee.exception.ResourceNotFoundException;
import com.micorservices.employee.repository.EmployeeRepository;
import com.micorservices.employee.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
//    private RestTemplate restTemplate;
//    private WebClient webClient;
    private APIClient apiClient;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee existsEmployee = employeeRepository.findByEmail(employeeDto.getEmail());
        if (existsEmployee != null){
            throw new EmailAlreadyExistsException("Email already exists");
        }
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public APIResponseDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User", "id", id)
                );
//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(
//                "http://localhost:8080/api/departments?departmentCode=" + employee.getDepartmentCode(),
//                DepartmentDto.class
//        );
//        DepartmentDto departmentDto = responseEntity.getBody();

//        DepartmentDto departmentDto = webClient
//                .get()
//                .uri("http://localhost:8080/api/departments?departmentCode=" + employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();

        DepartmentDto departmentDto = apiClient
                .getDepartmentById(employee.getDepartmentCode());
        APIResponseDto response = new APIResponseDto();
        response.setEmployee(modelMapper.map(employee, EmployeeDto.class));
        response.setDepartment(departmentDto);

        return response;
    }

    @Override
    public EmployeeDto getEmployeeByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email);
        return modelMapper.map(employee, EmployeeDto.class);
    }

//    private Employee mapToEmployee(EmployeeDto employeeDto){
//        Employee employee = new Employee();
//        employee.setId(employeeDto.getId());
//        employee.setFirstName(employeeDto.getFirstName());
//        employee.setLastName(employeeDto.getLastName());
//        employee.setEmail(employeeDto.getEmail());
//        return employee;
//    }
//
//    private EmployeeDto mapToEmployeeDto(Employee employee){
//        EmployeeDto employeeDto = new EmployeeDto();
//        employeeDto.setId(employee.getId());
//        employeeDto.setFirstName(employee.getFirstName());
//        employeeDto.setLastName(employee.getLastName());
//        employeeDto.setEmail(employee.getEmail());
//        return employeeDto;}
}
