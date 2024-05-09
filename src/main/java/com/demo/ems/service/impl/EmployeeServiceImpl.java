package com.demo.ems.service.impl;

import com.demo.ems.dto.EmployeeDto;
import com.demo.ems.entity.Employee;
import com.demo.ems.exception.ResourceNotFoundException;
import com.demo.ems.mapper.EmployeeMapper;
import com.demo.ems.repository.EmployeeRepository;
import com.demo.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee =   employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
      Employee employee=  employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exist with given id : "+employeeId ,employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long empID, EmployeeDto employeeDto) {

        Employee employee=  employeeRepository.findById(empID)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Employee is not exist with given id : "+empID ,empID));

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());

      Employee updatedEmployee =    employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long empId) {

        Employee employee=  employeeRepository.findById(empId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Employee is not exist with given id : "+empId ,empId));

        employeeRepository.deleteById(empId);

    }
}
