package map.project.demo.Controller;

import map.project.demo.Domain.Cabinet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import map.project.demo.Service.CabinetService;
import java.util.List;

@RestController
public class CabinetController {
    @Autowired
    private CabinetService service;

    @PostMapping("/addCabinet")
    public String add(@RequestBody Cabinet cabinet) {
        service.save(cabinet);
        return "Added successfully.";
    }

    @GetMapping("/findByIdentifierCabinet/{cabinetID}")
    public Cabinet findCabinetByIdentifier(@PathVariable int cabinetID) {
        return service.get(cabinetID);
    }

    @GetMapping("/getAllCabinets")
    public List<Cabinet> getAll() {
        return service.listAll();
    }

    @GetMapping("/printAllCabinets")
    public String printAll() {
        List<Cabinet> cabinets = service.listAll();
        cabinets.forEach(cabinet -> System.out.println(cabinet.toString()));
        return "Printed.";
    }

    @GetMapping("/deleteCabinet/{cabinetID}")
    public String delete(@PathVariable int cabinetID) {
        Cabinet cabinet = service.get(cabinetID);
        if (cabinet != null) {
            service.delete(cabinetID);
            return "Deleted successfully.";
        } else {
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }

    @GetMapping("/updateCabinet/{cabinetID}")
    public String update(@PathVariable int cabinetID,@RequestBody Cabinet newObject) {
        if(service.get(cabinetID) != null) {
            delete(cabinetID);
            add(newObject);
            return "Updated succesfully.";
        }
        else{
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }

}