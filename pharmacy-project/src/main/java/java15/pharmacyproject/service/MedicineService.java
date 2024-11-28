package java15.pharmacyproject.service;

import java15.pharmacyproject.entity.Medicine;
import java15.pharmacyproject.entity.Pharmacy;

import java.util.List;

public interface MedicineService {
    List<Medicine> findAll();

    Medicine save(Medicine medicine);

    void assignMedicineToPharmacy(Long id, Long pharmacyId);

    List<Medicine> searchByName(String keyword);

    List<Medicine> sortByName(String order);
}
