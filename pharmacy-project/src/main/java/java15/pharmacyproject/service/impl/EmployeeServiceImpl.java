package java15.pharmacyproject.service.impl;

import java15.pharmacyproject.entity.Employee;
import java15.pharmacyproject.entity.Pharmacy;
import java15.pharmacyproject.exception.NotFoundException;
import java15.pharmacyproject.repository.EmployeeRepository;
import java15.pharmacyproject.repository.PharmacyRepository;
import java15.pharmacyproject.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PharmacyRepository pharmacyRepository;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void assignEmployeeToPharmacy(Long id, Long pharmacyId) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found with ID: " + id));
        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId)
                .orElseThrow(() -> new NotFoundException("Pharmacy not found with ID: " + pharmacyId));
        employee.setPharmacy(pharmacy);
        employeeRepository.save(employee);
    }

    @Override
    public int getEmployeeAverageAge() {
        Double averageAge = employeeRepository.getEmployeeAverageAge();
        return averageAge != null ? averageAge.intValue() : 0;
    }

    @Override
    public List<Employee> sortBySalary(String order) {
        return sortByNameAscOrDesc(order);
    }

    @Override
    public Pharmacy getPharmacyByEmployeeId(Long id) {
        return employeeRepository.findById(id)
                .map(Employee::getPharmacy)
                .orElse(null);
    }

    private RowMapper<Employee> employeeRowMapper() {
        return (resultSet, rowNum) -> {
            Employee employee = new Employee();
            employee.setId(resultSet.getLong("id"));
            employee.setName(resultSet.getString("name"));
            employee.setEmail(resultSet.getString("email"));
            employee.setSalary(resultSet.getBigDecimal("salary"));
            employee.setAddress(resultSet.getString("address"));
            employee.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
            return employee;
        };
    }

    private List<Employee> sortByNameAscOrDesc(String order) {
        String sortOrder = "asc".equalsIgnoreCase(order) ? "asc" : "desc";
        String sql = """
        SELECT id, name, email, salary, address, date_of_birth
        FROM employees
        ORDER BY name %s
        """.formatted(sortOrder);
        return jdbcTemplate.query(sql, employeeRowMapper());
    }

}
