package com.hieudz.mapstruct.controller;

import com.hieudz.mapstruct.dto.DepartmentRequest;
import com.hieudz.mapstruct.dto.DepartmentResponse;
import com.hieudz.mapstruct.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<DepartmentResponse> getAllDepartment(@RequestParam(required = false) String search) {
        return departmentService.getAllDepartment(search);
    }

    @PostMapping
    public void save(@RequestBody DepartmentRequest departmentRequest) {
        departmentService.save(departmentRequest);
    }

    @GetMapping("/{id}")
    public DepartmentResponse getDepartmentById(@PathVariable Long id){
       return departmentService.getDepartmentById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        departmentService.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll() {
        departmentService.deleteAll();
    }
}
