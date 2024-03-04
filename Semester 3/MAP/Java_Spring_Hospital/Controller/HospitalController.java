package map.project.demo.Controller;

import map.project.demo.Domain.Hospital;
import map.project.demo.Repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import map.project.demo.Service.HospitalService;

import java.util.List;

@RestController
public class HospitalController {
    @Autowired private HospitalService service;

    @PostMapping("/addHospital")
    public String add(@RequestBody Hospital hospital) {
        service.save(hospital);
        return "Added successfully";
    }

    @GetMapping("/findByIdentifierHospital/{hospitalID}")
    public Hospital findHospitalByIdentifier(@PathVariable int hospitalID) {
        return service.get(hospitalID);
    }

    @GetMapping("/getAllHospitals")
    public List<Hospital> getAll() {
        return service.listAll();
    }

    @GetMapping("/printAllHospital")
    public String printAll() {
        List<Hospital> hospitals = service.listAll();
        hospitals.forEach(hospital -> System.out.println(hospital.toString()));
        return "Printed.";
    }

    @GetMapping("/deleteHospital/{hospitalID}")
    public String delete(@PathVariable int hospitalID) {
        Hospital hospital = service.get(hospitalID);
        if (hospital != null) {
            service.delete(hospitalID);
            return "Deleted successfully.";
        } else {
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }

    @GetMapping("/updateHospital/{hospitalID}")
    public String update(@PathVariable int hospitalID, @RequestBody Hospital newObject) {
        Hospital existingHospital = service.get(hospitalID);
        if (existingHospital != null) {
            delete(hospitalID);
            add(newObject);
            return "Updated successfully.";
        } else {
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }
}
