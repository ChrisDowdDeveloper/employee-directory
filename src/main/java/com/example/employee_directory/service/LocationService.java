package com.example.employee_directory.service;

import java.util.List;

import com.example.employee_directory.dto.LocationDto;

public interface LocationService {
    LocationDto getById(Long id);
    List<LocationDto> getAll();
    LocationDto create(LocationDto dto);
    LocationDto update(Long id, LocationDto dto);
    void delete(Long id);
}
