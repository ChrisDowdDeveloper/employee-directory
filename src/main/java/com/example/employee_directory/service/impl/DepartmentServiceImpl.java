package com.example.employee_directory.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.employee_directory.domain.Department;
import com.example.employee_directory.dto.DepartmentDto;
import com.example.employee_directory.exception.NotFoundException;
import com.example.employee_directory.mapper.DepartmentMapper;
import com.example.employee_directory.repository.DepartmentRepository;
import com.example.employee_directory.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepo;
    private final DepartmentMapper mapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepo, DepartmentMapper mapper) {
        this.departmentRepo = departmentRepo;
        this.mapper = mapper;
    }


    @Override
    public DepartmentDto getById(Long id) {
        Department d = departmentRepo.findById(id).orElseThrow(() -> new NotFoundException("Department not found"));
        return mapper.toDto(d);
    }

    @Override
    public List<DepartmentDto> getAll() {
        return departmentRepo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public DepartmentDto create(DepartmentDto dto) {
        Department department = mapper.fromDto(dto);
        return mapper.toDto(departmentRepo.save(department));
    }

    @Override
    public DepartmentDto update(Long id, DepartmentDto dto) {
        Department existing = departmentRepo.findById(id).orElseThrow(() -> new NotFoundException("Department not found"));
        mapper.updateFromDto(dto, existing);
        return mapper.toDto(departmentRepo.save(existing));
    }

    @Override
    public void delete(Long id) {
        if(!departmentRepo.existsById(id)) {
            throw new NotFoundException("Department not found");
        }

        departmentRepo.deleteById(id);
    }
    
}
