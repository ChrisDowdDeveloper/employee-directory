package com.example.employee_directory.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.employee_directory.domain.Role;
import com.example.employee_directory.dto.EmployeeDto;
import com.example.employee_directory.dto.RoleDto;

@Component
public class RoleMapper {
    
    private final EmployeeMapper employeeMapper;

    public RoleMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    public RoleDto toDto(Role r) {
        RoleDto dto = new RoleDto();

        dto.setId(r.getId());
        dto.setTitle(r.getTitle());
        dto.setDescription(r.getDescription());

        List<EmployeeDto> employeeDtos = r.getEmployees().stream().map(employeeMapper::toDto).collect(Collectors.toList());

        dto.setEmployees(employeeDtos);
        return dto;
    }

    public Role fromDto(RoleDto dto) {
        Role r = new Role();

        r.setTitle(dto.getTitle());
        r.setDescription(dto.getDescription());

        return r;
    }

    public void updateFromDto(RoleDto dto, Role r) {
        r.setTitle(dto.getTitle());
        r.setDescription(dto.getDescription());
    }

}
