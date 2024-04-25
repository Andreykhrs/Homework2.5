package pro.sky.employeebookhw25;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService serviceEmployee;

    public EmployeeController(EmployeeService serviceEmployee) {
        this.serviceEmployee = serviceEmployee;
        }

    @GetMapping("/add")
    public String add(@RequestParam(value = "firstName", required = false) String firstName,
                        @RequestParam(value = "lastName", required = false) String lastName) {
        if (firstName == null || lastName == null) {
            return "Неверные параметры.";
        }
        try {

            return String.valueOf(serviceEmployee.addEmployee(firstName, lastName));
        } catch (EmployeeStorageIsFullException e) {
            return e.getMessage();
        }
        catch (EmployeeAlreadyAddedException e) {
            return e.getMessage();
        }
    }
    @GetMapping("/find")
    public String find(@RequestParam(value = "firstName", required = false) String firstName,
                      @RequestParam(value = "lastName", required = false) String lastName) {
        if (firstName == null || lastName == null) {
            return "Неверные параметры.";
        }
        try {

            return String.valueOf(serviceEmployee.findEmployee(firstName, lastName));
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        }
    }
    @GetMapping("/remove")
    public String remove(@RequestParam(value = "firstName", required = false) String firstName,
                      @RequestParam(value = "lastName", required = false) String lastName) {
        if (firstName == null || lastName == null) {
            return "Неверные параметры.";
        }

        return String.valueOf(serviceEmployee.removeEmployee(firstName,lastName));
    }

    @GetMapping("/print")
    public String print() {
        return serviceEmployee.printEmployees();
    }
}
