package map.project.demo.Service;

import map.project.demo.Domain.Medication;
import map.project.demo.Repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationService {
    @Autowired
    private MedicationRepository medicationRepository;
    public List<Medication> listAll() {
        return (List<Medication>) medicationRepository.findAll();
    }


    public void save(Medication medication) {
        medicationRepository.save(medication);
    }

    public Medication get(Integer id) {
        return medicationRepository.findByMedicationID(id);
    }

    public void delete(Integer id) {
        medicationRepository.deleteById(id);
    }

}
