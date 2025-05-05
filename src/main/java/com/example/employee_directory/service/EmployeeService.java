package com.example.employee_directory.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.employee_directory.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto getById(Long id);
    List<EmployeeDto> getAll();
    EmployeeDto create(EmployeeDto dto);
    EmployeeDto update(Long id, EmployeeDto dto);
    void delete(Long id);
    Page<EmployeeDto> search(String name, Long roleId, Long locationId, Pageable pageable);
    Map<String, Long> countByDepartment();
    Map<String, Long> countByRole();
    Map<String, Long> countByLocation();
}
