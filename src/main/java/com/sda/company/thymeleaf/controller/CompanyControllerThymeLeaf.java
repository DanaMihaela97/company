package com.sda.company.thymeleaf.controller;

import com.sda.company.dto.CompanyInfoDto;
import com.sda.company.service.CompanyService;
import com.sda.company.thymeleaf.model.WelcomeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller

public class CompanyControllerThymeLeaf {

    private final CompanyService companyService;
    @Autowired
    public CompanyControllerThymeLeaf(@Qualifier("companyServiceImpl") CompanyService companyService){
        this.companyService = companyService;
    }
    @RequestMapping(value="/start")
    public String index(){
        return "start";
    }

    @RequestMapping(value="/welcome", method= RequestMethod.POST)
    public String getWelcomeForm(@ModelAttribute (name="welcomeForm") WelcomeDto welcomeDto, Model model){
        // vom primi, prin intermediul acelui welcomedto, numele si checkbox ul de la front-end.
        // daca user ul nu si a trecut numele, nu il las mai departe + mesaj, la fel si pt checkbox.
        if (welcomeDto.getName().isBlank()){
            model.addAttribute("errorMessage", "Please enter your name!");
            return "start";
        }
        if (!welcomeDto.isGdprConsent()){
            model.addAttribute("errorMessage", welcomeDto.getName() + " GDPR consent is mandatory to continue!");
            return "start";
        }
        return "home";
    }
    @RequestMapping(value="/showAll", method = RequestMethod.GET)
    public String getAllCompanies(Model model){
        List<CompanyInfoDto>  companyInfoDtos = companyService.getCompaniesFullInfo();
        model.addAttribute("companyList", companyInfoDtos);

        return "companyTable";
    }
}
