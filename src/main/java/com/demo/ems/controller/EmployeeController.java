package com.demo.ems.controller;

import com.demo.ems.dto.EmployeeDto;
import com.demo.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
@Slf4j
public class EmployeeController {
    private EmployeeService employeeService;

     @PostMapping
     public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
         EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
         return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
     }


     @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeBuId(@PathVariable("id") Long empId){
         EmployeeDto employeeDto = employeeService.getEmployeeById(empId);
         return ResponseEntity.ok(employeeDto);
     }


     @GetMapping
     public ResponseEntity<List<EmployeeDto>> getAllEEmployees(){
         List<EmployeeDto> employees = employeeService.getAllEmployees();
         return ResponseEntity.ok(employees);
     }



     @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long empId,@RequestBody EmployeeDto  employeeDto){
        EmployeeDto employees = employeeService.updateEmployee(empId,employeeDto );
        return ResponseEntity.ok(employees);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long empId){
         employeeService.deleteEmployee(empId);
         return ResponseEntity.ok("Employee deleted successfully : "+ empId);
    }
}
