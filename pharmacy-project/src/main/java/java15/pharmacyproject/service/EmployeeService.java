package java15.pharmacyproject.service;

import java15.pharmacyproject.entity.Employee;
import java15.pharmacyproject.entity.Pharmacy;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee save(Employee employee);

    void assignEmployeeToPharmacy(Long id, Long pharmacyId);

    int getEmployeeAverageAge();

    List<Employee> sortBySalary(String order);

    Pharmacy getPharmacyByEmployeeId(Long id);
}
