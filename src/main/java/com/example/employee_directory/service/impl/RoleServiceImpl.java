package com.example.employee_directory.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.example.employee_directory.domain.Role;
import com.example.employee_directory.dto.RoleDto;
import com.example.employee_directory.exception.NotFoundException;
import com.example.employee_directory.mapper.RoleMapper;
import com.example.employee_directory.repository.RoleRepository;
import com.example.employee_directory.service.RoleService;

public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepo;
    private final RoleMapper mapper;

    public RoleServiceImpl(RoleRepository roleRepo, RoleMapper mapper) {
        this.roleRepo = roleRepo;
        this.mapper = mapper;
    }

    @Override
    public RoleDto getById(Long id) {
        Role r = roleRepo.findById(id).orElseThrow(() -> new NotFoundException("Role not found"));
        return mapper.toDto(r);
    }

    @Override
    public List<RoleDto> getAll() {
        return roleRepo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public RoleDto create(RoleDto dto) {
        Role role = mapper.fromDto(dto);
        return mapper.toDto(roleRepo.save(role));
    }

    @Override
    public RoleDto update(Long id, RoleDto dto) {
        Role existing = roleRepo.findById(id).orElseThrow(() -> new NotFoundException("Role not found"));
        mapper.updateFromDto(dto, existing);
        return mapper.toDto(roleRepo.save(existing));
    }

    @Override
    public void delete(Long id) {
        if(!roleRepo.existsById(id)) {
            throw new NotFoundException("Role not found");
        }

        roleRepo.deleteById(id);
    }
    
}
