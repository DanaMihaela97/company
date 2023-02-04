package com.sda.company.convertor;

import com.sda.company.dto.EmployeeCreateDto;
import com.sda.company.dto.EmployeeInfoDto;
import com.sda.company.dto.EmployeeShortInfoDto;
import com.sda.company.model.Employee;

public class EmployeeConvertor {
    //entity = model (DB)
    public static Employee createEmployeeDtoToEntity(EmployeeCreateDto employeeCreateDto){
        Employee employee = new Employee();
        employee.setName(employeeCreateDto.getName()); // employee din db o sa fie completat cu datele transformate in createtdo de la frontend
        employee.setCnp(employeeCreateDto.getCnp());
        employee.setEmail(employeeCreateDto.getEmail());
        employee.setAddress(employeeCreateDto.getAddress());
        employee.setPhoneNumber(employeeCreateDto.getPhoneNumber());

        return employee;
    }
    public static EmployeeInfoDto convertEmployeeEntityToInfoDto(Employee employee){
        EmployeeInfoDto employeeInfoDto = new EmployeeInfoDto();
        employeeInfoDto.setName(employee.getName());
        employeeInfoDto.setCnp(employee.getCnp());
        employeeInfoDto.setEmail(employee.getEmail());
        employeeInfoDto.setAddress(employee.getAddress());
        employeeInfoDto.setPhoneNumber(employee.getPhoneNumber());
        employeeInfoDto.setId(employee.getId());
        employeeInfoDto.setSalary(employee.getSalary());
         employeeInfoDto.setCompany(employee.getCompany());

        return employeeInfoDto;
    }
    public static EmployeeShortInfoDto convertEmployeeEntityToShortInfoDto(Employee employee){
        EmployeeShortInfoDto employeeShortInfoDto = new EmployeeShortInfoDto();
        employeeShortInfoDto.setName(employee.getName());
        employeeShortInfoDto.setCnp(employee.getCnp());

        return employeeShortInfoDto;
    }
}
