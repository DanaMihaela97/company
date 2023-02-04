package com.sda.company.dto;


import com.sda.company.model.Company;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class EmployeeInfoDto {
    private String name;
    private Long cnp;
    private String email;
    private String address;
    private String phoneNumber;
    private Long salary;
    private Integer id;
    private CompanyShortInfoDto companyShortInfoDto;





}
