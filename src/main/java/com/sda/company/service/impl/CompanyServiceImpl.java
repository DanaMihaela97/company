package com.sda.company.service.impl;

import com.sda.company.convertor.CompanyConvertor;
import com.sda.company.dto.CompanyCreateDto;
import com.sda.company.dto.CompanyInfoDto;
import com.sda.company.dto.CompanyShortInfoDto;
import com.sda.company.model.Company;
import com.sda.company.model.Employee;
import com.sda.company.repository.CompanyRepository;
import com.sda.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    // service face legatura cu repository

    // @Autowired - field injection => not recommended
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    //create company
    public CompanyInfoDto createCompany (CompanyCreateDto companyCreateDto) {
        Company company = companyRepository.save(CompanyConvertor.createDtoToEntity(companyCreateDto));
        CompanyInfoDto companyInfoDto = CompanyConvertor.convertEntityToInfoDto(company);

        return companyInfoDto;
    }

    @Override
    public List<CompanyShortInfoDto> getAllCompanies() {
        List<CompanyShortInfoDto> companyShortInfoDto = new ArrayList<>();
        companyRepository.findAll().forEach(company ->companyShortInfoDto.add(CompanyConvertor.convertEntityToShortInfoDto(company)));

        return companyShortInfoDto;
    }

    @Override
        public Optional<CompanyInfoDto> findCompanyByName(String name) {
            Optional<Company> company = companyRepository.findByName(name);
            CompanyInfoDto companyInfoDto = null;
            if (company.isPresent()) {
                companyInfoDto = CompanyConvertor.convertEntityToInfoDto(company.get());
            }
            return Optional.ofNullable(companyInfoDto);
        }

    @Override
    public void generateCompanies(List<Company> companies) {
        companyRepository.saveAll(companies);
        System.out.println(companies.size() + " companies were generated!");
    }

    @Override
    public Optional<CompanyInfoDto> getCompanyById(Integer id) {
        Optional<Company> company = companyRepository.getCompanyById(id);
        CompanyInfoDto companyInfoDto = null;
        if (company.isPresent()){
            companyInfoDto = CompanyConvertor.convertEntityToInfoDto(company.get());

        }
        return Optional.ofNullable(companyInfoDto);
    }

    @Override
    public void deleteCompany(Integer id) {
        companyRepository.deleteById(id);
    }


}
