package java15.pharmacyproject.api;

import java15.pharmacyproject.entity.Employee;
import java15.pharmacyproject.entity.Pharmacy;
import java15.pharmacyproject.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeAPI {
    private final EmployeeService employeeService;

    @GetMapping
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @PostMapping
    public Employee save(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @PutMapping("/{id}/assign")
    public String assign(@PathVariable Long id, @RequestParam Long pharmacyId) {
        employeeService.assignEmployeeToPharmacy(id, pharmacyId);
        return "Employee assigned to Pharmacy successfully!";
    }

    @GetMapping("/employee-average-age")
    public int getEmployeeAverageAge() {
        return employeeService.getEmployeeAverageAge();
    }

    @GetMapping("/sort")
    public List<Employee> sort(@RequestParam String order) {
        return employeeService.sortBySalary(order);
    }

    @GetMapping("/{id}/get-employee-pharmacy")
    public Pharmacy getPharmacyByEmployeeId(@PathVariable Long id) {
        return employeeService.getPharmacyByEmployeeId(id);
    }
}
