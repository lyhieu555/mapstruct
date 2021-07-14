package com.hieudz.mapstruct.service.Impl;

import com.hieudz.mapstruct.dto.DepartmentRequest;
import com.hieudz.mapstruct.dto.DepartmentResponse;
import com.hieudz.mapstruct.entities.Department;
import com.hieudz.mapstruct.exception.SpringException;
import com.hieudz.mapstruct.mapper.DepartmentMapper;
import com.hieudz.mapstruct.repository.DepartmentRepository;
import com.hieudz.mapstruct.service.DepartmentService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger logger = Logger.getLogger(DepartmentServiceImpl.class);

    private final DepartmentMapper departmentMapper;
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentMapper departmentMapper, DepartmentRepository departmentRepository) {
        this.departmentMapper = departmentMapper;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<DepartmentResponse> getAllDepartment(String search) {
        if (search != null) {
             List<DepartmentResponse> departmentResponses = departmentRepository.findByAll(search)
                    .stream()
                    .map(departmentMapper::entityToDto)
                    .collect(Collectors.toList());
             if (departmentResponses.size() > 0){
                 logger.info("getAllDepartment::" + departmentResponses.size());
                 return departmentResponses;
             }else {
                 logger.info("getAllDepartment:: null");
             }

        }
         List<DepartmentResponse> departmentResponses = departmentRepository.findAll()
                .stream()
                .map(departmentMapper::entityToDto)
                .collect(Collectors.toList());
        if (departmentResponses.size() > 0){
            logger.info("getAllDepartment::" + departmentResponses.size());
            return departmentResponses;
        }else {
            logger.info("getAllDepartment:: null");
            return null;
        }
    }

    @Override
    public void save(DepartmentRequest departmentRequest) {
        Optional<Department> name = departmentRepository.findByName(departmentRequest.getName());

        if (name.isPresent()) {
            logger.error(departmentRequest.getName() + " đã tồn tại");
            throw new SpringException(departmentRequest.getName() + " đã tồn tại");
        } else {
            departmentRepository.save(departmentMapper.dtoToEntity(departmentRequest));
        }
    }

    @Override
    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }


    @Override
    public void deleteAll() {
        departmentRepository.deleteAll();
    }

    @Override
    public DepartmentResponse getDepartmentById(Long id){
        Department department = departmentRepository.findById(id).orElseThrow(() -> new SpringException("Không tìm thấy id phòng ban: " + id ));
        return departmentMapper.entityToDto(department);
    }
}
