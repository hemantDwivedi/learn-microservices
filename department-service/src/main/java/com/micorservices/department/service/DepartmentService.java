package com.micorservices.department.service;

import com.micorservices.department.dto.DepartmentDto;

public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartmentById(Long id);
    DepartmentDto getDepartmentByCode(String departmentCode);
}
