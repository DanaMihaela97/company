package com.sda.company.controller;

import com.sda.company.components.CustomFakerEmployee;
import com.sda.company.dto.EmployeeCreateDto;
import com.sda.company.dto.EmployeeInfoDto;
import com.sda.company.dto.EmployeeShortInfoDto;
import com.sda.company.dto.JobTitle;
import com.sda.company.model.Employee;
import com.sda.company.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employee")
public class
EmployeeController {
    private final EmployeeService employeeService;
    private final CustomFakerEmployee customFakerEmployee;


    @Autowired
    public EmployeeController(EmployeeService employeeService, CustomFakerEmployee customFakerEmployee) {
        this.employeeService = employeeService;
        this.customFakerEmployee = customFakerEmployee;

    }


    @PostMapping("/create")
    public ResponseEntity<EmployeeInfoDto> createEmployee(@RequestBody @Valid EmployeeCreateDto employeeCreateDto) {
        EmployeeInfoDto employeeInfoDto = employeeService.createEmployee(employeeCreateDto);
        return ResponseEntity.ok(employeeInfoDto);
    }

    @GetMapping("/generateEmployee")
    public ResponseEntity<String> generateEmployee() {
        employeeService.generateEmployee(customFakerEmployee.generateDummyEmployees());
        return ResponseEntity.ok("Employees were added");
    }

    @GetMapping("/getAllEmployees")
    public ResponseEntity<List<EmployeeShortInfoDto>> getAllEmployees() {
        /*List<EmployeeShortInfoDto> allEmployees = employeeService.getAllEmployees();
        return ResponseEntity.ok(allEmployees); */

        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/getEmployeeByName")
    public ResponseEntity<Optional<EmployeeInfoDto>> getEmployeeByName(@RequestParam String name) {
        Optional<EmployeeInfoDto> employeeInfoDto = employeeService.findEmployeeByName(name);
        return ResponseEntity.ok(employeeInfoDto);
    }

    @PutMapping("/updateEmployeeSalary")
    public ResponseEntity<String> updateSalary(@RequestBody Map<String, String> json) {

        employeeService.updateEmployeeSalary(json.get("name"),Long.parseLong(json.get("salary")));
        return ResponseEntity.ok("Salary updated");

    }
    @PutMapping("/updateEmployeeTitle")
    public ResponseEntity<String> updateTitle(@RequestBody Map<String, String> json){
        employeeService.updateEmployeeTitle(json.get("name"), JobTitle.valueOf(json.get("job_title")));

        return ResponseEntity.ok("Job Title added.");
    }
    @GetMapping("/getEmployeeById")
    public ResponseEntity<EmployeeInfoDto> getEmpById(@RequestParam Integer id){
        Optional<EmployeeInfoDto> employeeInfoDto = employeeService.getEmployeeById(id);
        return ResponseEntity.of(employeeInfoDto);
    }
    @PutMapping("/updateEmpCompany")
    public ResponseEntity<String> updateEmpCompany(@RequestBody Map<String, String> json){
        employeeService.updateEmployeeCompany(Integer.parseInt(json.get("emp_id")), Integer.parseInt(json.get("company_id")));
        return ResponseEntity.ok("Emp company updated.");
    }
    @PutMapping("/updateEmpSalary")
    public ResponseEntity<String> updateEmpSalary(@RequestBody Map<String, String> json){
        employeeService.updateEmplSalary(Integer.parseInt(json.get("emp_id")), Long.parseLong(json.get("salary")));
        return ResponseEntity.ok("Salary updated!");
    }
    @PutMapping("/updateEmpJobTitle")
    public ResponseEntity<String> updateJobTitle(@RequestBody Map<String, String> json){
        employeeService.updateEmpJobTitle(Integer.parseInt(json.get("emp_id")), JobTitle.valueOf(json.get("job_title")));
        return ResponseEntity.ok("JobTitle updated!");
    }
    @DeleteMapping("/deleteEmployeeById")
    public ResponseEntity<String> deleteEmployeeById(@RequestParam Integer id){
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok("Employee deleted!");
    }

}
