package java15.pharmacyproject.repository;

import java15.pharmacyproject.entity.Medicine;
import java15.pharmacyproject.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
    @Query("select p from Pharmacy p where p.name ilike %:keyword%")
    List<Pharmacy> searchByName(String keyword);

    @Query("""
    select p from Pharmacy p
    where size(p.employees) = (
        select max(size(p2.employees) ) from Pharmacy p2
    )
    """)
    List<Pharmacy> getHighEmploymentPharmacy();

    @Query("SELECT p.medicines FROM Pharmacy p WHERE p.id = :pharmacyId")
    Set<Medicine> findMedicinesByPharmacyId(@Param("pharmacyId") Long pharmacyId);
}