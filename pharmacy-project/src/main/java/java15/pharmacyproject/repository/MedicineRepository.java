package java15.pharmacyproject.repository;

import java15.pharmacyproject.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    @Query("select m from Medicine m where m.name ilike %:keyword%")
    List<Medicine> searchByName(String keyword);
}
