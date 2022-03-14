package com.sageit.employee.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sageit.employee.exception.EmployeeNotFoundException;
import com.sageit.employee.model.Employee;
import com.sageit.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	// @Autowired
	// private EmployeeRepository employeeRepository;

	private EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public Employee getEmployeeById(Integer id) {
		return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id", id));

	}

	public Employee updateEmployee(Employee employee, Integer id) {
		Employee existingEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id", id));
		existingEmployee.setEmpName(employee.getEmpName());
		existingEmployee.setSalary(employee.getSalary());
		existingEmployee.setDepartment(employee.getDepartment());
		existingEmployee.setEmail(employee.getEmail());
		employeeRepository.save(existingEmployee);
		return existingEmployee;
	}

	public void deleteEmployee(Integer id) {
		employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id", id));
		employeeRepository.deleteById(id);
	}

}
