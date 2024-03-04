package map.project.demo.Controller;

import map.project.demo.Domain.Surgery;
import map.project.demo.Service.SurgeryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SurgeryController {
    @Autowired private SurgeryService service;
    @PostMapping("/addSurgery")
    public String add(@RequestBody Surgery surgery) {
        service.save(surgery);
        return "Added successfully.";
    }

    @GetMapping("/findByIdentifierSurgery/{surgeryID}")
    public Surgery findSurgeryByIdentifier(@PathVariable int surgeryID) {
        return service.get(surgeryID);
    }

    @GetMapping("/getAllSurgery")
    public List<Surgery> getAll() {
        return service.listAll();
    }

    @GetMapping("/printAllSurgery")
    public String printAll() {
        List<Surgery> surgeries = service.listAll();
        surgeries.forEach(surgery -> System.out.println(surgery.toString()));
        return "Printed.";
    }

    @GetMapping("/deleteSurgery/{surgeryID}")
    public String delete(@PathVariable int surgeryID) {
        Surgery surgery = service.get(surgeryID);
        if (surgery != null) {
            service.delete(surgeryID);
            return "Deleted successfully.";
        } else {
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }

    @GetMapping("/updateSurgery/{surgeryID}")
    public String update(@PathVariable int surgeryID, @RequestBody Surgery newObject) {
        Surgery existingSurgery = service.get(surgeryID);
        if (existingSurgery != null) {
            delete(surgeryID);
            add(newObject);
            return "Updated successfully.";
        } else {
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }
}
