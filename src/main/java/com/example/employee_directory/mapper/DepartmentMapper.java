package com.example.employee_directory.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.employee_directory.domain.Department;
import com.example.employee_directory.dto.DepartmentDto;
import com.example.employee_directory.dto.EmployeeDto;

@Component
public class DepartmentMapper {

    private final EmployeeMapper employeeMapper;

    public DepartmentMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }
    
    public DepartmentDto toDto(Department d) {
        DepartmentDto dto = new DepartmentDto();

        dto.setId(d.getId());
        dto.setName(d.getName());
        dto.setDescription(d.getDescription());
        
        List<EmployeeDto> employeeDtos = d.getEmployees().stream().map(employeeMapper::toDto).collect(Collectors.toList());

        dto.setEmployees(employeeDtos);
        return dto;
    }

    public Department fromDto(DepartmentDto dto) {
        Department d = new Department();

        d.setName(dto.getName());
        d.setDescription(dto.getDescription());

        return d;

    }

    public void updateFromDto(DepartmentDto dto, Department d) {
        d.setName(dto.getName());
        d.setDescription(dto.getDescription());
    }

}
