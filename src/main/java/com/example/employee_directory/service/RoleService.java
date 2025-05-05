package com.example.employee_directory.service;

import java.util.List;

import com.example.employee_directory.dto.RoleDto;

public interface RoleService {
    RoleDto getById(Long id);
    List<RoleDto> getAll();
    RoleDto create(RoleDto dto);
    RoleDto update(Long id, RoleDto dto);
    void delete(Long id);
}
