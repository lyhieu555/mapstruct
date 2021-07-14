package com.hieudz.mapstruct.service;

import com.hieudz.mapstruct.dto.DepartmentRequest;
import com.hieudz.mapstruct.dto.DepartmentResponse;

import java.util.List;

public interface DepartmentService {

    DepartmentResponse getDepartmentById(Long id);

    List<DepartmentResponse> getAllDepartment(String search);

    void save(DepartmentRequest departmentRequest);

    void deleteById(Long id);

    void deleteAll();
}
