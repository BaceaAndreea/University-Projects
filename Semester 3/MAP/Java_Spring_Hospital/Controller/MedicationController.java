package map.project.demo.Controller;

import map.project.demo.Domain.Medication;
import map.project.demo.Repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import map.project.demo.Service.MedicationService;

import java.util.List;

@RestController
public class MedicationController {
    @Autowired private MedicationService service;

    @PostMapping("/addMedication")
    public String add(@RequestBody Medication medication) {
        service.save(medication);
        return "Added successfully";
    }

    @GetMapping("/findByIdentifierMedication/{medicationID}")
    public Medication findMedicationByIdentifier(@PathVariable int medicationID) {
        return service.get(medicationID);
    }

    @GetMapping("/getAllMedication")
    public List<Medication> getAll() {
        return service.listAll();
    }

    @GetMapping("/printAllMedication")
    public String printAll() {
        List<Medication> medications = service.listAll();
        medications.forEach(medication -> System.out.println(medication.toString()));
        return "Printed";
    }

    @GetMapping("/deleteMedication/{medicationID}")
    public String delete(@PathVariable int medicationID) {
        Medication medication = service.get(medicationID);
        if (medication != null) {
            service.delete(medicationID);
            return "Deleted successfully.";
        } else {
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }

    @GetMapping("/updateMedication/{medicationID}")
    public String update(@PathVariable int medicationID, @RequestBody Medication newObject) {
        Medication existingMedication = service.get(medicationID);
        if (existingMedication != null) {
            delete(medicationID);
            add(newObject);
            return "Updated successfully.";
        } else {
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }
}
