package map.project.demo.Service;

import map.project.demo.Domain.Doctor;

import map.project.demo.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
    public List<Doctor> listAll() {
        return (List<Doctor>) doctorRepository.findAll();
    }

    public void save(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public Doctor get(Integer doctorID) {
        return doctorRepository.findByDoctorID(doctorID);
    }

    public void delete(Integer id) {
        doctorRepository.deleteById(id);
    }
}
