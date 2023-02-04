package com.sda.company.dto;

import com.github.javafaker.Company;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class EmployeeCreateDto {
    @NotBlank(message = "Employee name is mandatory.")
    private String name;
    @Min(value = 13)
    private Long cnp;
    @Email(message = "Invalid email.")
    @NotBlank(message = "This field cannot be empty.")
    private String email;
    private String address;
    private String phoneNumber;
    private Long salary;
    private Company company;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCnp() {
        return cnp;
    }

    public void setCnp(Long cnp) {
        this.cnp = cnp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
    public void setCompany(Company company){
        this.company=company;
    }
    public Company getCompany(){
        return company;
    }

}
