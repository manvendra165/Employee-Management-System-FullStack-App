package net.javaguides.ems.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.mapper.EmployeeMapper;
import net.javaguides.ems.repository.EmployeeRepository;
import net.javaguides.ems.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        // map employee dto to employee entity
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        // save employee in db
        Employee savedEmployee = employeeRepository.save(employee);
        // map to EmployeeDto
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);

    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {

        Employee getEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with given Id:" + employeeId + " is not exist"));
        return EmployeeMapper.mapToEmployeeDto(getEmployee);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {

        // check employee is available with given id or not
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exist with the given Id: " + employeeId));
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Employee saveDetailOfEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(saveDetailOfEmployee);
    }

    @Override
    public void deleteEmployeeById(Long employeeId) {
        // check employee is available with given id or not
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exist with the given Id: " + employeeId));

        employeeRepository.deleteById(employeeId);
    }
}
