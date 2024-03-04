package map.project.demo.Service;

import map.project.demo.Repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import map.project.demo.Domain.Hospital;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {
    @Autowired private HospitalRepository hospitalRepository;
    public List<Hospital> listAll() {
        return (List<Hospital>) hospitalRepository.findAll();
    }


    public void save(Hospital hospital) {
        hospitalRepository.save(hospital);
    }

    public Hospital get(Integer id) {
        return hospitalRepository.findByHospitalID(id);
    }

    public void delete(Integer id) {
        hospitalRepository.deleteById(id);
    }

}
