package com.example.employee_directory.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.employee_directory.domain.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("""
        SELECT e FROM Employee e
        WHERE (:name IS NULL OR LOWER(e.firstName) LIKE LOWER(CONCAT('%', :name, '%'))
            OR LOWER(e.lastName) LIKE LOWER(CONCAT('%', :name, '%')))
        AND (:roleId IS NULL OR e.role.id = :roleId)
        AND (:locationId IS NULL OR e.location.id = :locationId)
    """)
    Page<Employee> search(
        @Param("name") String name, 
        @Param("roleId") Long roleId, 
        @Param("locationId") Long locationId, 
        org.springframework.data.domain.Pageable pageable
    );

    @Query("SELECT e.department.name AS name, COUNT(e) AS count FROM Employee e GROUP BY e.department.name")
    List<Object[]> countByDepartment();

    @Query("SELECT e.role.name AS name, COUNT(e) AS count FROM Employee e GROUP BY e.role.name")
    List<Object[]> countByRole();
    
    @Query("SELECT e.location.name AS name, COUNT(e) AS count FROM Employee e GROUP BY e.location.name")
    List<Object[]> countByLocation();
} 
