package java15.pharmacyproject.service;

import java15.pharmacyproject.entity.Medicine;
import java15.pharmacyproject.entity.Pharmacy;

import java.util.List;
import java.util.Set;

public interface PharmacyService {
    List<Pharmacy> findAll();

    Pharmacy save(Pharmacy pharmacy);

    List<Pharmacy> searchByName(String keyword);

    List<Pharmacy> getHighEmploymentPharmacy();

    Set<Medicine> getMedicinesByPharmacyId(Long pharmacyId);
}
