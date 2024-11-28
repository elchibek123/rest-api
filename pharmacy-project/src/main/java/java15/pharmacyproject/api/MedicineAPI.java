package java15.pharmacyproject.api;

import java15.pharmacyproject.entity.Medicine;
import java15.pharmacyproject.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
@RequiredArgsConstructor
public class MedicineAPI {
    private final MedicineService medicineService;

    @GetMapping
    public List<Medicine> findAll() {
        return medicineService.findAll();
    }

    @PostMapping
    public Medicine save(@RequestBody Medicine medicine) {
        return medicineService.save(medicine);
    }

    @PutMapping("/{id}/assign")
    public String assign(@PathVariable Long id, @RequestParam Long pharmacyId) {
        medicineService.assignMedicineToPharmacy(id, pharmacyId);
        return "Medicine assigned to Pharmacy successfully!";
    }

    @GetMapping("/search")
    public List<Medicine> search(@RequestParam String keyword) {
        return medicineService.searchByName(keyword);
    }

    @GetMapping("/sort")
    public List<Medicine> sort(@RequestParam String order) {
        return medicineService.sortByName(order);
    }
}
