package map.project.demo.Controller;

import map.project.demo.Service.PatientService;
import map.project.demo.Domain.Patient;
import map.project.demo.Iterator.PatientIterator;
import map.project.demo.Domain.PatientIteratorImpl;
import map.project.demo.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class PatientController {
    @Autowired private PatientService service;

    @PostMapping("/addPatient")
    public String add(@RequestBody Patient patient) {
        service.save(patient);
        return "Added successfully.";
    }

    @GetMapping("/findByIdentifierPatient/{patientID}")
    public Patient findPatientByIdentifier(@PathVariable int patientID) {
        return service.get(patientID);
    }

    @GetMapping("/getAllPatient")
    public List<Patient> getAll() {
        return service.listAll();
    }

    @GetMapping("/printAllPatient")
    public String printAll() {
        List<Patient> patients = service.listAll();
        patients.forEach(patient -> System.out.println(patient.toString()));
        return "Printed";
    }

    @GetMapping("/deletePatient/{patientID}")
    public String delete(@PathVariable int patientID) {
        Patient patient = service.get(patientID);
        if (patient != null) {
            service.delete(patientID);
            return "Deleted successfully.";
        } else {
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }

    @GetMapping("/updatePatient/{patientID}/{newObject}")
    public String update(@PathVariable int patientID, @RequestBody Patient newObject) {
        Patient existingPatient = service.get(patientID);
        if (existingPatient != null) {
            delete(patientID);
            add(newObject);
            return "Updated successfully.";
        } else {
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }


    @GetMapping("iteratePatients")
    public String iteratePatients() {
        PatientIterator<Patient> iterator = service.createIterator();
        while (iterator.hasNext()) {
            Patient patient = iterator.next();
            System.out.println(patient);
        }
        return "Iterator successful.";
    }
}
