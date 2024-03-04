package map.project.demo.Controller;

import map.project.demo.Domain.Disease;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import map.project.demo.Service.DiseaseService;

import java.util.List;

@RestController
public class DiseaseController {
    @Autowired private DiseaseService service;

    @PostMapping("/addDisease")
    public String add(@RequestBody Disease disease) {
        service.save(disease);
        return "Added successfully.";
    }

    @GetMapping("/findDiseaseByIdDisease/{diseaseID}")
    public Disease findDiseaseByIdentifier(@PathVariable int diseaseID) {
        return service.get(diseaseID);
    }

    @GetMapping("/getAllDiseases")
    public List<Disease> getAll() {
        return service.listAll();
    }

    @GetMapping("/printAllDiseases")
    public String printAll() {
        List<Disease> diseases = service.listAll();
        diseases.forEach(disease -> System.out.println(disease.toString()));
        return "Printed.";
    }

    @GetMapping("/deleteDisease/{diseaseID}")
    public String delete(@PathVariable int diseaseID) {
        Disease disease = service.get(diseaseID);
        if (disease != null) {
            service.delete(diseaseID);
            return "Deleted successfully.";
        } else {
            throw new IllegalArgumentException("Nothing was found for the provided disease identifier.");
        }
    }

    @GetMapping("/updateDisease/{diseaseID}")
    public String update(@PathVariable int diseaseID, @RequestBody Disease newObject) {
        if (service.get(diseaseID) != null) {
            delete(diseaseID);
            add(newObject);
            return "Updated successfully.";
        } else {
            throw new IllegalArgumentException("Nothing was found for the provided disease identifier.");
        }
    }
}
