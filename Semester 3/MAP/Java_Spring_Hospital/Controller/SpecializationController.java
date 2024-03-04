package map.project.demo.Controller;

import map.project.demo.Domain.Specialization;
import map.project.demo.Repository.SpecializationRepository;
import map.project.demo.Service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SpecializationController {
    @Autowired
    private SpecializationService service;

    @PostMapping("/addSpecialization")
    public String add(@RequestBody Specialization specialization) {
        service.save(specialization);
        return "Added successfully";
    }

    @GetMapping("/findByIdentifierSpecialization/{specializationID}")
    public Specialization findSpecializationByIdentifier(@PathVariable int specializationID) {
        return service.get(specializationID);
    }

    @GetMapping("/getAllSpecializations")
    public List<Specialization> getAll() {
        return service.listAll();
    }

    @GetMapping("/printAllSpecializations")
    public String printAll() {
        List<Specialization> specializations = service.listAll();
        specializations.forEach(specialization -> System.out.println(specialization.toString()));
        return "Printed";
    }

    @GetMapping("/deleteSpecialization/{specializationID}")
    public String delete(@PathVariable int specializationID) {
        Specialization specialization = service.get(specializationID);
        if (specialization != null) {
            service.delete(specializationID);
            return "Deleted successfully";
        } else {
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }

    @GetMapping("/updateSpecialization/{specializationID}")
    public String update(@PathVariable int specializationID, @RequestBody Specialization newObject) {
        Specialization existingSpecialization = service.get(specializationID);
        if (existingSpecialization != null) {
            delete(specializationID);
            add(newObject);
            return "Updated successfully";
        } else {
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }
}
