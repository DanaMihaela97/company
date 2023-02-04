package com.sda.company.service;

import com.sda.company.dto.EmployeeCreateDto;
import com.sda.company.dto.EmployeeInfoDto;
import com.sda.company.dto.EmployeeShortInfoDto;
import com.sda.company.dto.JobTitle;
import com.sda.company.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    EmployeeInfoDto createEmployee (EmployeeCreateDto employeeCreateDto);

    void generateEmployee(List<Employee> employeeList);

    List<EmployeeShortInfoDto> getAllEmployees();
    Optional<EmployeeInfoDto> findEmployeeByName(String name);
   void updateEmployeeSalary(String name, Long salary);
   void updateEmployeeTitle(String name, JobTitle jobTitle);
   Optional<EmployeeInfoDto> getEmployeeById(Integer id);
   void updateEmployeeCompany(Integer id, Integer companyId);

    void updateEmplSalary(Integer id, Long salary);
    void updateEmpJobTitle(Integer id, JobTitle jobTitle);
    void deleteEmployeeById(Integer id);

}
