package com.example.employee_directory.mapper;

import org.springframework.stereotype.Component;

import com.example.employee_directory.domain.Department;
import com.example.employee_directory.domain.Employee;
import com.example.employee_directory.domain.Location;
import com.example.employee_directory.domain.Role;
import com.example.employee_directory.dto.EmployeeDto;

@Component
public class EmployeeMapper {
    
    public EmployeeDto toDto(Employee e) {
        EmployeeDto dto = new EmployeeDto();

        dto.setId(e.getId());
        dto.setFirstName(e.getFirstName());
        dto.setLastName(e.getLastName());
        dto.setEmail(e.getEmail());
        dto.setDepartmentId(e.getDepartment().getId());
        dto.setRoleId(e.getRole().getId());
        dto.setLocationId(e.getLocation().getId());

        return dto;
    }

    public Employee fromDto(EmployeeDto dto, Department dept, Role role, Location loc) {
        Employee e = new Employee();

        e.setFirstName(dto.getFirstName());
        e.setLastName(dto.getLastName());
        e.setEmail(dto.getEmail());
        e.setDepartment(dept);
        e.setRole(role);
        e.setLocation(loc);

        return e;
    }

    public void updateFromDto(EmployeeDto dto, Employee e, Department dept, Role role, Location loc) {
        e.setFirstName(dto.getFirstName());
        e.setLastName(dto.getLastName());
        e.setEmail(dto.getEmail());
        e.setDepartment(dept);
        e.setLocation(loc);
        e.setRole(role);
    }

}
