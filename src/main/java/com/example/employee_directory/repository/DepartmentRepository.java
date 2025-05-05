package com.example.employee_directory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employee_directory.domain.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{}
