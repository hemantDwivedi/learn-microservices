package com.micorservices.department.controller;

import com.micorservices.department.dto.DepartmentDto;
import com.micorservices.department.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentController {
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        return new ResponseEntity<>(departmentService.saveDepartment(departmentDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id){
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @GetMapping
    public ResponseEntity<DepartmentDto> getDepartmentById(@RequestParam String departmentCode){
        return ResponseEntity.ok(departmentService.getDepartmentByCode(departmentCode));
    }

}