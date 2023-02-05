package com.sda.company.controller;
import com.sda.company.components.CustomFakerCompany;
import com.sda.company.dto.CompanyCreateDto;
import com.sda.company.dto.CompanyInfoDto;
import com.sda.company.dto.CompanyShortInfoDto;
import com.sda.company.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/company")
@ControllerAdvice
public class CompanyController {
    // controller face legatura cu service
    private final CompanyService companyService;
    private final CustomFakerCompany customFakerCompany;
    @Autowired // am injectat
    public CompanyController(@Qualifier("companyServiceImpl") CompanyService companyService, CustomFakerCompany customFakerCompany) {
        this.companyService = companyService;
        this.customFakerCompany = customFakerCompany;
    }

    // CREATE COMPANY
    @PostMapping("/create")
    public ResponseEntity<CompanyInfoDto> createCompany(@RequestBody @Valid CompanyCreateDto companyCreateDto, Principal principal){
        companyCreateDto.setCreatedBy(principal.getName());
        CompanyInfoDto companyInfoDto = companyService.createCompany(companyCreateDto);                     // apelam metoda de createCompany din IMPL
         return ResponseEntity.ok(companyInfoDto);
    }
    // FIND ALL
    @GetMapping("/getAllCompanies")
    public ResponseEntity<List<CompanyShortInfoDto>> getAllCompanies () {
        List<CompanyShortInfoDto> allCompanies = companyService.getAllCompanies();
        return ResponseEntity.ok(allCompanies);
        // return ResponseEntity.ok(companyService.getAllCompanies()); => alternative
    }
    @GetMapping("/findCompanyByName")
    public ResponseEntity<CompanyInfoDto> getCompanyByName(@RequestParam String name) {
        CompanyInfoDto companyInfoDto = companyService.findCompanyByName(name);
        return ResponseEntity.ok(companyInfoDto);
    }

    @GetMapping("/generateCompanies")
    public ResponseEntity<String> generateCompanies(){
        companyService.generateCompanies(customFakerCompany.generateDummyCompanies());
        return ResponseEntity.ok("Companies were generated!:)");
    }
    @GetMapping("/getCompanyById")
    public ResponseEntity<CompanyInfoDto> getCompanyById(@RequestParam Integer id){
        Optional<CompanyInfoDto> companyInfoDto = companyService.getCompanyById(id);
        return ResponseEntity.of(companyInfoDto);
    }
    @DeleteMapping("/deleteCompanyById")
    public ResponseEntity<String> deleteCompanyById(@RequestParam Integer id){
        companyService.deleteCompany(id);
        return ResponseEntity.ok("Company deleted!");
    }
    @GetMapping("/getAllCompaniesFullDetails")
    public ResponseEntity<List<CompanyInfoDto>> getFullDetails(){
        List<CompanyInfoDto> companyInfoDtos = companyService.getCompaniesFullInfo();
        return ResponseEntity.ok(companyInfoDtos);
    }

}
