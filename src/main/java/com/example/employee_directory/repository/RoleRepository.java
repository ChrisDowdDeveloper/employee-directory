package com.example.employee_directory.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employee_directory.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {}
