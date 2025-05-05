package com.example.employee_directory.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.employee_directory.domain.Location;
import com.example.employee_directory.dto.EmployeeDto;
import com.example.employee_directory.dto.LocationDto;

@Component
public class LocationMapper {
    
    private final EmployeeMapper employeeMapper;
    public LocationMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    public LocationDto toDto(Location l) {
        LocationDto dto = new LocationDto();

        dto.setId(l.getId());
        dto.setName(l.getName());
        dto.setAddress(l.getAddress());

        List<EmployeeDto> employeeDtos = l.getEmployees().stream().map(employeeMapper::toDto).collect(Collectors.toList());

        dto.setEmployees(employeeDtos);
        return dto;
    }

    public Location fromDto(LocationDto dto) {
        Location l = new Location();

        l.setName(dto.getName());
        l.setAddress(dto.getAddress());

        return l;
    }

    public void updateFromDto(LocationDto dto, Location l) {
        l.setName(dto.getName());
        l.setAddress(dto.getAddress());
    }
}
