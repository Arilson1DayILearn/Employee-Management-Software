package com.software.employeemanager.controller;

import com.software.employeemanager.model.EmployeeDto;
import com.software.employeemanager.model.Employee;
import com.software.employeemanager.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeResource {
    private final EmployeeService employeeService;

    @Autowired
    private ModelMapper modelMapper;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
        List<EmployeeDto> employeesDto = employeeService.findAllEmployees()
                .stream().map(this::toEmployeeDto).collect(Collectors.toList());
        return new ResponseEntity<>(employeesDto, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long id){
        EmployeeDto employeeDto = toEmployeeDto(employeeService.findEmployeeById(id));
        return new ResponseEntity<>((employeeDto), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<EmployeeDto> addEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        EmployeeDto newEmployeeDto = toEmployeeDto(employeeService.addEmployee(toEmployee(employeeDto)));
        return new ResponseEntity<>(newEmployeeDto, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<EmployeeDto> updateEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        EmployeeDto updateEmployeeDto = toEmployeeDto(employeeService.updateEmployee(toEmployee(employeeDto)));
        return new ResponseEntity<>(updateEmployeeDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private EmployeeDto toEmployeeDto(Employee employee){
        return modelMapper.map(employee, EmployeeDto.class);
    }

    private Employee toEmployee(EmployeeDto employeeDto){
        return modelMapper.map(employeeDto, Employee.class);
    }


}
