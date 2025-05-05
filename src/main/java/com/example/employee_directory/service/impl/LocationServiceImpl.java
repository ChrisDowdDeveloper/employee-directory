package com.example.employee_directory.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.employee_directory.domain.Location;
import com.example.employee_directory.dto.LocationDto;
import com.example.employee_directory.exception.NotFoundException;
import com.example.employee_directory.mapper.LocationMapper;
import com.example.employee_directory.repository.LocationRepository;
import com.example.employee_directory.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepo;
    private final LocationMapper mapper;

    public LocationServiceImpl(LocationRepository locationRepo, LocationMapper mapper) {
        this.locationRepo = locationRepo;
        this.mapper = mapper;
    }

    @Override
    public LocationDto getById(Long id) {
        Location l = locationRepo.findById(id).orElseThrow(() -> new NotFoundException("Location not found"));
        return mapper.toDto(l);
    }

    @Override
    public List<LocationDto> getAll() {
        return locationRepo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public LocationDto create(LocationDto dto) {
        Location location = mapper.fromDto(dto);
        return mapper.toDto(locationRepo.save(location));
    }

    @Override
    public LocationDto update(Long id, LocationDto dto) {
        Location existing = locationRepo.findById(id).orElseThrow(() -> new NotFoundException("Location not found"));
        mapper.updateFromDto(dto, existing);
        return mapper.toDto(locationRepo.save(existing));
    }

    @Override
    public void delete(Long id) {
        if(!locationRepo.existsById(id)) {
            throw new NotFoundException("Location not found");
        }

        locationRepo.deleteById(id);
    }
    
}
