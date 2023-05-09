package com.micorservices.employee.service.impl;

import com.micorservices.employee.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient  {
    @GetMapping("/api/departments")
    DepartmentDto getDepartmentById(@RequestParam String departmentCode);
}