package com.sda.company.repository;

import com.sda.company.dto.EmployeeCreateDto;
import com.sda.company.dto.EmployeeInfoDto;
import com.sda.company.model.Company;
import com.sda.company.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    Optional<Employee> findEmployeeByName(String name);
    Optional<Employee> getEmployeesById(Integer id);
    List<Employee> findAllByCompanyId(Integer companyId);




}
