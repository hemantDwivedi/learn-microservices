package com.micorservices.department.service.impl;

import com.micorservices.department.dto.DepartmentDto;
import com.micorservices.department.entity.Department;
import com.micorservices.department.exception.DepartmentCodeAlreadyExistsException;
import com.micorservices.department.exception.ResourceNotFoundException;
import com.micorservices.department.repository.DepartmentRepository;
import com.micorservices.department.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;
    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        // convert DepartmentDto to Department entity
        Department department1 = departmentRepository.findByDepartmentCode(departmentDto.getDepartmentCode());
        if (department1 != null){
            throw new DepartmentCodeAlreadyExistsException("DEPARTMENT CODE");
        }
        Department department = modelMapper.map(departmentDto, Department.class);
        Department savedDepartment = departmentRepository.save(department);
        return modelMapper.map(savedDepartment, DepartmentDto.class);
    }

    @Override
    public DepartmentDto getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Department", "id", id)
                );
        return modelMapper.map(department, DepartmentDto.class);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode);
        return modelMapper.map(department, DepartmentDto.class);
    }

//    private Department mapToDepartment(DepartmentDto departmentDto){
//       return new Department(
//                departmentDto.getId(),
//                departmentDto.getDepartmentName(),
//                departmentDto.getDepartmentDescription(),
//                departmentDto.getDepartmentCode()
//        );
//    }
//
//    private DepartmentDto mapToDepartmentDto(Department department){
//        return new DepartmentDto(
//                department.getId(),
//                department.getDepartmentName(),
//                department.getDepartmentDescription(),
//                department.getDepartmentCode()
//        );
//    }
}
