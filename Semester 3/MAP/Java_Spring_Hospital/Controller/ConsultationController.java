package map.project.demo.Controller;

import map.project.demo.Domain.Consultation;
import map.project.demo.Repository.ConsultationRepository;
import map.project.demo.Service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConsultationController {
    @Autowired
    private ConsultationService service;

    @PostMapping("/addConsultation")
    public String add(@RequestBody Consultation consultation) {
        service.save(consultation);
        return "Added successfully.";
    }

    @GetMapping("/findByIdentifierConsultation/{consultationID}")
    public Consultation findConsultationByIdentifier(@PathVariable int consultationID) {
        return service.get(consultationID);
    }

    @GetMapping("/getAllConsultation")
    public List<Consultation> getAll() {
        return service.listAll();
    }

    @GetMapping("/printAllConsultation")
    public String printAll() {
        List<Consultation> consultations = service.listAll();
        consultations.forEach(consultation -> System.out.println(consultation.toString()));
        return "Printed.";    }

    @GetMapping("/deleteConsultation/{consultationID}")
    public String delete(@PathVariable int consultationID) {
        Consultation consultation = service.get(consultationID);
        if (consultation != null) {
            service.delete(consultationID);
            return "Deleted successfully.";
        } else {
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }

    @GetMapping("/updateConsultation/{consultationID}")
    public String update(@PathVariable int consultationID, @RequestBody Consultation newObject) {
        Consultation existingConsultation = service.get(consultationID);
        if (existingConsultation != null) {
            delete(consultationID);
            add(newObject);
            return "Updated successfully.";
        } else {
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }
}
