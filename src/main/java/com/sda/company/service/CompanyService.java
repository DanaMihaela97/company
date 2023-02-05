package com.sda.company.service;

import com.sda.company.components.CustomFakerCompany;
import com.sda.company.dto.*;
import com.sda.company.model.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    CompanyInfoDto createCompany (CompanyCreateDto companyCreateDto);

    List<CompanyShortInfoDto> getAllCompanies();

    CompanyInfoDto findCompanyByName(String name);

    // primim o lista de obiecte si le salvam, nu vreau un raspuns la postman
    void generateCompanies(List<Company> companies);
    Optional<CompanyInfoDto> getCompanyById(Integer id);
    void deleteCompany(Integer id);
    List<CompanyInfoDto> getCompaniesFullInfo();









}
