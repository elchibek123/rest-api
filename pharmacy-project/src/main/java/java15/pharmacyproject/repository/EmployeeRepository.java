package java15.pharmacyproject.repository;

import java15.pharmacyproject.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT AVG(YEAR(CURRENT_DATE) - YEAR(e.dateOfBirth)) " +
            "FROM Employee e")
    Double getEmployeeAverageAge();
}