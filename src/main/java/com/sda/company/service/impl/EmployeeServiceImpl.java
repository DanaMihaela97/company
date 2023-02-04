package com.sda.company.service.impl;

import com.sda.company.convertor.CompanyConvertor;
import com.sda.company.convertor.EmployeeConvertor;
import com.sda.company.dto.*;
import com.sda.company.exception.EmployeeException;
import com.sda.company.model.Company;
import com.sda.company.model.Employee;
import com.sda.company.repository.CompanyRepository;
import com.sda.company.repository.EmployeeRepository;
import com.sda.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyRepository companyRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public EmployeeInfoDto createEmployee(EmployeeCreateDto employeeCreateDto) {
        Employee employee = employeeRepository.save(EmployeeConvertor.createEmployeeDtoToEntity(employeeCreateDto));
        EmployeeInfoDto employeeInfoDto = EmployeeConvertor.convertEmployeeEntityToInfoDto(employee);

        return employeeInfoDto;
    }

    @Override
    public void generateEmployee(List<Employee> employees) {
        employeeRepository.saveAll(employees);

        System.out.println(employees.size() + " employees were generated successfully.");

    }

    @Override
    public List<EmployeeShortInfoDto> getAllEmployees() {
    List<EmployeeShortInfoDto> employeeShortInfoDtoList = new ArrayList<>(); // lista de employee cu name si cnp din infodot vor fi puse intr un array list
    employeeRepository.findAll().forEach(employee->employeeShortInfoDtoList.add(EmployeeConvertor.convertEmployeeEntityToShortInfoDto(employee)));
    // mergem in db sa gasim employee. convertim din model in shortinfodto si ii adaugam in lista de mai sus
    return employeeShortInfoDtoList;
    }

    @Override
    public EmployeeInfoDto findEmployeeByName(String name) {
        Employee employee = employeeRepository.findEmployeeByName(name)
                .orElseThrow(()->new EmployeeException(("Employee with name: " + name + " not found!")));

        return EmployeeConvertor.convertEmployeeEntityToInfoDto(employee);
    }

    @Override
    public void updateEmployeeSalary(String name, Long salary) {
        Employee employee = employeeRepository.findEmployeeByName(name).get();
        employee.setSalary(salary);
        employeeRepository.save(employee);
       /* Employee employee=employeeRepository.getEmployeesById(id).get();
        employee.setSalary(salary);

        employeeRepository.save(employee); */

    }

    @Override
    public void updateEmployeeTitle(String name, JobTitle jobTitle) {
       Employee employee = employeeRepository.findEmployeeByName(name).get();
        employee.setJob_title(jobTitle.toString());
        employeeRepository.save(employee);
        /*Employee employee = employeeRepository.getEmployeesById(id).get();
        employee.setJob_title(jobTitle.toString());
        employeeRepository.save(employee); */
    }


    @Override //// SALARY APARE NULL IN POSTMAN////
    public Optional<EmployeeInfoDto> getEmployeeById(Integer id) {
        Optional<Employee> employee = employeeRepository.getEmployeesById(id);
        EmployeeInfoDto employeeInfoDto = null;
        if(employee.isPresent()){
            employeeInfoDto = EmployeeConvertor.convertEmployeeEntityToInfoDto(employee.get());
        }
        return Optional.ofNullable(employeeInfoDto);
    }

     @Override
    public void updateEmployeeCompany(Integer empId, Integer companyId) {
        Employee employee=employeeRepository.getEmployeesById(empId).get();
        Company company = companyRepository.getCompanyById(companyId).get();

        employee.setCompany(companyRepository.getCompanyById(companyId).get());
        company.setEmployees(employeeRepository.getEmployeesById(empId).get());

        employeeRepository.save(employee);
        companyRepository.save(company);

    }

    @Override
    public void updateEmplSalary(Integer id, Long salary) {
        Employee employee=employeeRepository.getEmployeesById(id).get();
        employee.setSalary(salary);
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmpJobTitle(Integer id, JobTitle jobTitle) {
        Employee employee=employeeRepository.getEmployeesById(id).get();
        employee.setJob_title(jobTitle.toString());

        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeShortInfoDto> findAllByCompanyId(Integer companyId) {
       List<Employee> employees = employeeRepository.findAllByCompanyId(companyId);
       List<EmployeeShortInfoDto> response = new ArrayList<>();

       employees.forEach(employee->{response.add(EmployeeConvertor.convertEmployeeEntityToShortInfoDto(employee));});
       return response;
    }

   /* @Override
    public EmployeeInfoDto employeePersonToCompany(Integer companyId, Integer employeeId) {
        Optional<Company> company = companyRepository.findById(companyId);
        Optional<Employee> employee=employeeRepository.getEmployeesById(employeeId);
        EmployeeInfoDto employeeInfoDto = null;
        CompanyInfoDto companyInfoDto=null;
        if (employee.isPresent()){
            employeeInfoDto = EmployeeConvertor.convertEmployeeEntityToInfoDto(employee.get());
        }
       if(company.isPresent()){
           companyInfoDto = CompanyConvertor.convertEntityToInfoDto(company.get());
       }
       return Optional.ofNullable(employeeInfoDto);
         */




}



