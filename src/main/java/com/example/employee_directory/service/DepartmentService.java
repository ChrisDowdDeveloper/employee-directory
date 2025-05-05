package com.example.employee_directory.service;

import java.util.List;

import com.example.employee_directory.dto.DepartmentDto;

public interface DepartmentService {
    DepartmentDto getById(Long id);
    List<DepartmentDto> getAll();
    DepartmentDto create(DepartmentDto dto);
    DepartmentDto update(Long id, DepartmentDto dto);
    void delete(Long id);
}
