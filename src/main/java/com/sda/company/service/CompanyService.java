package com.sda.company.service;

import com.sda.company.components.CustomFakerCompany;
import com.sda.company.dto.CompanyCreateDto;
import com.sda.company.dto.CompanyInfoDto;
import com.sda.company.dto.CompanyShortInfoDto;
import com.sda.company.dto.EmployeeInfoDto;
import com.sda.company.model.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    CompanyInfoDto createCompany (CompanyCreateDto companyCreateDto);

    List<CompanyShortInfoDto> getAllCompanies();

    Optional<CompanyInfoDto> findCompanyByName(String name);

    // primim o lista de obiecte si le salvam, nu vreau un raspuns la postman
    void generateCompanies(List<Company> companies);
    Optional<CompanyInfoDto> getCompanyById(Integer id);
    void deleteCompany(Integer id);







}
