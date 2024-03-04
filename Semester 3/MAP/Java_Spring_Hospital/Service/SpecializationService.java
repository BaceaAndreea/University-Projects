package map.project.demo.Service;

import map.project.demo.Domain.Specialization;
import map.project.demo.Repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecializationService {
    @Autowired
    private SpecializationRepository specializationRepository;

    public List<Specialization> listAll() {
        return (List<Specialization>) specializationRepository.findAll();
    }

    public void save(Specialization specialization) {
        specializationRepository.save(specialization);
    }

    public Specialization get(Integer id) {
        return specializationRepository.findBySpecializationID(id);
    }

    public void delete(Integer id) {
        specializationRepository.deleteById(id);
    }
}
