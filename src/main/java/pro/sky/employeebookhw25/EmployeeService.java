package pro.sky.employeebookhw25;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final int maxNumberOfEmployees=3;

    List<Employee> employees = new ArrayList<>();

    public Employee addEmployee(String firstName, String lastName) {
        if (this.employees.size() >= maxNumberOfEmployees) {
            throw new EmployeeStorageIsFullException();
        }
        for (Employee employee:employees) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                throw new EmployeeAlreadyAddedException();
            }
        }
        Employee employee = new Employee(firstName, lastName);
        employees.add(employee);
        System.out.println("Добавлен сотрудник " + employee.toString());
        return employee;
    }

    public Employee findEmployee(String firstName, String lastName) {
        System.out.println("Ищу сотрудника " + firstName + " " + lastName);
        for (Employee employee : employees) {
            if (employee.getFirstName().contains(firstName) && employee.getLastName().contains(lastName)) {
                return employee;
            }
        }
        System.out.println("Сотрудник " + firstName + " " + lastName + " не найден.");
        throw new EmployeeNotFoundException();
    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = findEmployee(firstName,lastName);
        employees.remove(employee);
        return employee;
    }

    public String printEmployees() {
        String allText = "";
        for (int i = 0; i < employees.size(); i++) {
            allText=allText+ employees.get(i).toString();
        }
        return allText;
    }


}
