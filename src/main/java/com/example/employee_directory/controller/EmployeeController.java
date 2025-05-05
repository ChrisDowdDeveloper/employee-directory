package com.example.employee_directory.controller;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee_directory.dto.EmployeeDto;
import com.example.employee_directory.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<EmployeeDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public EmployeeDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/search")
    public Page<EmployeeDto> search(@RequestParam(required = false) String name, @RequestParam(required = false) Long roleId, @RequestParam(required = false) Long locationId, @PageableDefault(size = 10) Pageable pageable) {
        return service.search(name, roleId, locationId, pageable);
    }

    @GetMapping("/count-by-department")
    public Map<String, Long> countByDepartment() {
        return service.countByDepartment();
    }

    @GetMapping("/count-by-role")
    public Map<String, Long> countByRole() {
        return service.countByRole();
    }

    @GetMapping("/count-by-location")
    public Map<String, Long> countByLocation() {
        return service.countByLocation();
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> create(@RequestBody @Validated EmployeeDto dto) {
        EmployeeDto saved = service.create(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public EmployeeDto update(@PathVariable Long id, @RequestBody @Validated EmployeeDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
