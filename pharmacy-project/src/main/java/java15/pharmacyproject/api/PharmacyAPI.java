package java15.pharmacyproject.api;

import java15.pharmacyproject.entity.Medicine;
import java15.pharmacyproject.entity.Pharmacy;
import java15.pharmacyproject.service.PharmacyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/pharmacies")
@RequiredArgsConstructor
public class PharmacyAPI {
    private final PharmacyService pharmacyService;

    @GetMapping
    public List<Pharmacy> findAll() {
        return pharmacyService.findAll();
    }

    @PostMapping
    public Pharmacy save(@RequestBody Pharmacy pharmacy) {
        return pharmacyService.save(pharmacy);
    }

    @GetMapping("/search")
    public List<Pharmacy> search(@RequestParam String keyword) {
        return pharmacyService.searchByName(keyword);
    }

    @GetMapping("/highest-employment")
    public List<Pharmacy> getHighEmploymentPharmacy() {
        return pharmacyService.getHighEmploymentPharmacy();
    }

    @GetMapping("/{pharmacyId}/medicines")
    public Set<Medicine> getMedicinesByPharmacyId(@PathVariable Long pharmacyId) {
        Set<Medicine> medicines = pharmacyService.getMedicinesByPharmacyId(pharmacyId);
        if (medicines.isEmpty()) {
            return null;
        }
        return medicines;
    }
}
