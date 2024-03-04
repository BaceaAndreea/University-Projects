package map.project.demo.Controller;


import map.project.demo.Domain.Doctor;
import map.project.demo.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoctorController {
    @Autowired
    private DoctorService service;

    @Autowired
    public DoctorController(DoctorService service) {
        this.service = service;
    }

    @PostMapping("/addDoctor")
    public String add(@RequestBody Doctor doctor) {
        service.save(doctor);
        return "Added successfully.";
    }

    @GetMapping("/findByIdentifierDoctor/{doctorID}")
    public Doctor findDoctorByIdentifier(@PathVariable int doctorID) {
        return service.get(doctorID);
    }

    @GetMapping("/getAllDoctor")
    public List<Doctor> getAll() {
        return service.listAll();
    }

    @GetMapping("/printAllDoctors")
    public String printAll() {
        List<Doctor> doctors =  service.listAll();
        doctors.forEach(doctor -> System.out.println(doctor.toString()));
        return "Printed.";
    }

    @GetMapping("/deleteDoctor/{doctorID}")
    public String delete(@PathVariable int doctorID) {
        Doctor doctor = service.get(doctorID);
        if (doctor != null) {
            service.delete(doctorID);
            return "Deleted successfully.";
        } else {
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }


    @GetMapping("/updateDoctor/{doctorID}")
    public String update(@PathVariable int doctorID, @RequestBody Doctor newObject) {
        Doctor existingDoctor = service.get(doctorID);
        if (existingDoctor != null) {
            delete(doctorID);
            add(newObject);
            return "Updated successfully";
        } else {
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }
}
