package com.hieudz.mapstruct.mapper;

import com.hieudz.mapstruct.dto.DepartmentRequest;
import com.hieudz.mapstruct.dto.DepartmentResponse;
import com.hieudz.mapstruct.entities.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    Department dtoToEntity(DepartmentRequest departmentRequest);

    DepartmentResponse entityToDto(Department department);
}
