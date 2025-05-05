package com.example.employee_directory.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.employee_directory.domain.Department;
import com.example.employee_directory.domain.Employee;
import com.example.employee_directory.domain.Location;
import com.example.employee_directory.domain.Role;
import com.example.employee_directory.dto.EmployeeDto;
import com.example.employee_directory.exception.NotFoundException;
import com.example.employee_directory.mapper.EmployeeMapper;
import com.example.employee_directory.repository.DepartmentRepository;
import com.example.employee_directory.repository.EmployeeRepository;
import com.example.employee_directory.repository.LocationRepository;
import com.example.employee_directory.repository.RoleRepository;
import com.example.employee_directory.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepo;
    private final DepartmentRepository deptRepo;
    private final RoleRepository roleRepo;
    private final LocationRepository locRepo;
    private final EmployeeMapper mapper;

    public EmployeeServiceImpl(EmployeeRepository e, DepartmentRepository d, RoleRepository r, LocationRepository l, EmployeeMapper m) {
        this.employeeRepo = e;
        this.deptRepo = d;
        this.roleRepo = r;
        this.locRepo = l;
        this.mapper = m;
    }

    @Override
    public EmployeeDto getById(Long id) {
        return employeeRepo.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new NotFoundException("Employee not found"));
    }

    @Override
    public List<EmployeeDto> getAll() {
        List<Employee> employees = employeeRepo.findAll();
        return employees.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto create(EmployeeDto dto) {
        Department dept = deptRepo.findById(dto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Invalid Department"));
        Role role = roleRepo.findById(dto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Invalid Role"));
        Location loc = locRepo.findById(dto.getLocationId())
                .orElseThrow(() -> new RuntimeException("Invalid Location"));

        Employee e = mapper.fromDto(dto, dept, role, loc);
        return mapper.toDto(employeeRepo.save(e));
    }

    @Override
    public EmployeeDto update(Long id, EmployeeDto dto) {
        Employee e = employeeRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found"));

        Department dept = deptRepo.findById(dto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Invalid Department"));
        Role role = roleRepo.findById(dto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Invalid Role"));
        Location loc = locRepo.findById(dto.getLocationId())
                .orElseThrow(() -> new RuntimeException("Invalid Location"));

        mapper.updateFromDto(dto, e, dept, role, loc);
        return mapper.toDto(employeeRepo.save(e));
    }

    @Override
    public void delete(Long id) {
        if (!employeeRepo.existsById(id)) {
            throw new NotFoundException("Employee not found");
        }

        employeeRepo.deleteById(id);
    }

    @Override
    public Page<EmployeeDto> search(String name, Long roleId, Long locationId, Pageable pageable) {
        return employeeRepo.search(name, roleId, locationId, pageable).map(mapper::toDto);
    }

    @Override
    public Map<String, Long> countByDepartment() {
        return employeeRepo.countByDepartment().stream().collect(Collectors.toMap(obj -> (String) obj[0], obj -> (Long) obj[1]));
    }

    @Override
    public Map<String, Long> countByRole() {
        return employeeRepo.countByRole().stream().collect(Collectors.toMap(obj -> (String) obj[0], obj -> (Long) obj[1]));
    }

    @Override
    public Map<String, Long> countByLocation() {
        return employeeRepo.countByLocation().stream().collect(Collectors.toMap(obj -> (String) obj[0], obj -> (Long) obj[1]));
    }
}
