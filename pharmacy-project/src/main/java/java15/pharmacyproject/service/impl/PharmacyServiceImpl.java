package java15.pharmacyproject.service.impl;

import java15.pharmacyproject.entity.Medicine;
import java15.pharmacyproject.entity.Pharmacy;
import java15.pharmacyproject.repository.PharmacyRepository;
import java15.pharmacyproject.service.PharmacyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PharmacyServiceImpl implements PharmacyService {
    private final PharmacyRepository pharmacyRepository;

    @Override
    public List<Pharmacy> findAll() {
        return pharmacyRepository.findAll();
    }

    @Override
    public Pharmacy save(Pharmacy pharmacy) {
        return pharmacyRepository.save(pharmacy);
    }

    @Override
    public List<Pharmacy> searchByName(String keyword) {
        return pharmacyRepository.searchByName(keyword);
    }

    @Override
    public List<Pharmacy> getHighEmploymentPharmacy() {
        return pharmacyRepository.getHighEmploymentPharmacy();
    }

    @Override
    public Set<Medicine> getMedicinesByPharmacyId(Long pharmacyId) {
        return pharmacyRepository.findMedicinesByPharmacyId(pharmacyId);
    }
}
