package net.javaguides.ems.controller;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employees")
//@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // rest api to create employee
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return  new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // rest api to get employee by id
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId){
       EmployeeDto getEmployee = employeeService.getEmployeeById(employeeId);
       return ResponseEntity.ok(getEmployee);
    }

    // rest api to get all employee
    @GetMapping
    public ResponseEntity<List<EmployeeDto>>getAllEmployee(){
        List<EmployeeDto> getEmployeeList = employeeService.getAllEmployee();
        return ResponseEntity.ok(getEmployeeList);
    }

    // rest api to update employee
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto updatedEmployee){
        EmployeeDto savedEmployee = employeeService.updateEmployee(employeeId, updatedEmployee);
        return ResponseEntity.ok(savedEmployee);
    }

    // rest api to delete employee by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployeeById(employeeId);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}
