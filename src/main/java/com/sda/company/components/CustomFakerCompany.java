package com.sda.company.components;

import com.github.javafaker.Faker;
import com.sda.company.model.Company;

import java.util.ArrayList;
import java.util.List;

public class CustomFakerCompany {
    // o metoda care sa ne intoarca o lista de entitati, de company entities

    public List<Company> generateDummyCompanies(){
        List<Company> dummyCompanies = new ArrayList<>();
        Faker faker = new Faker();

        // obiectul faker face parte din libraria din maven. cu el o sa generam orice: nume, email, adrese, nr de tel, etc.
        for (int i = 0; i < 10; i++){
            Company company = new Company();
            company.setName(faker.company().name()); // poate genera nume de companii
            company.setRegistrationNumber(faker.number().randomNumber(5,true));
            company.setAddress(faker.address().fullAddress());
            company.setPhoneNumber(faker.phoneNumber().phoneNumber());
            company.setEmail(faker.bothify("?????##@company.com")); // regex

            dummyCompanies.add(company);
        }
        return dummyCompanies;
    }
}
