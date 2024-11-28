package java15.pharmacyproject.service.impl;

import java15.pharmacyproject.entity.Medicine;
import java15.pharmacyproject.entity.Pharmacy;
import java15.pharmacyproject.exception.NotFoundException;
import java15.pharmacyproject.repository.MedicineRepository;
import java15.pharmacyproject.repository.PharmacyRepository;
import java15.pharmacyproject.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepository medicineRepository;
    private final PharmacyRepository pharmacyRepository;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Medicine> findAll() {
        return medicineRepository.findAll();
    }

    @Override
    public Medicine save(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    @Override
    public void assignMedicineToPharmacy(Long id, Long pharmacyId) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Medicine not found with ID: " + id));
        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId)
                .orElseThrow(() -> new NotFoundException("Pharmacy not found with ID: " + pharmacyId));
        pharmacy.getMedicines().add(medicine);
        medicine.getPharmacies().add(pharmacy);
        pharmacyRepository.save(pharmacy);
        medicineRepository.save(medicine);
    }

    @Override
    public List<Medicine> searchByName(String keyword) {
        return medicineRepository.searchByName(keyword);
    }

    @Override
    public List<Medicine> sortByName(String order) {
        return sortByNameAscOrDesc(order);
    }

    private RowMapper<Medicine> medicineRowMapper() {
        return (resultSet, rowNum) -> {
            Medicine medicine = new Medicine();
            medicine.setId(resultSet.getLong("id"));
            medicine.setName(resultSet.getString("name"));
            medicine.setPrice(resultSet.getBigDecimal("price"));
            return medicine;
        };
    }

    private List<Medicine> sortByNameAscOrDesc(String order) {
        String sortOrder = "asc".equalsIgnoreCase(order) || "desc".equalsIgnoreCase(order) ? order : "asc";
        String sql = """
        select id, name, price
        from medicines
        order by name %s
        """.formatted(sortOrder);
        return jdbcTemplate.query(sql, medicineRowMapper());
    }
}
