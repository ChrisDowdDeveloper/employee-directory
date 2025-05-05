package com.example.employee_directory.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationDto {

    private Long id;

    private String name;

    private String address;

    private List<EmployeeDto> employees;
    
}
