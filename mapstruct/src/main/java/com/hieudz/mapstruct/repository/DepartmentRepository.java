package com.hieudz.mapstruct.repository;

import com.hieudz.mapstruct.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByName(String name);

    @Query("SELECT p FROM Department p WHERE " + " CONCAT(p.id, p.name)" + "LIKE %?1% ")
    List<Department> findByAll(String search);
}
