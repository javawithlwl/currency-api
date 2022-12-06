package com.lwl.currency.batch;

import com.lwl.currency.domain.Employee;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;


public class CustomItemProcessor implements ItemProcessor<EmployeeDto, Employee> {
  @Override
  public Employee process(EmployeeDto employeeDto) throws Exception {
    Employee employee = new Employee();
    employee.setId(Integer.parseInt(employeeDto.getId()));
    employee.setFirstName(employeeDto.getFirstName());
    employee.setLastName(employeeDto.getLastName());
    employee.setGender(employeeDto.getGender());
    employee.setSalary(Double.parseDouble(employeeDto.getSalary()));
    employee.setEmail(employeeDto.getEmail());
    return employee;
  }
}
