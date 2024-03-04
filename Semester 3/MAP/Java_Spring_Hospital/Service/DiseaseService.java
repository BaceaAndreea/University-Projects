package map.project.demo.Service;

import map.project.demo.Domain.Disease;
import map.project.demo.Repository.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseaseService {
    @Autowired
    private DiseaseRepository diseaseRepository;

    public List<Disease> listAll() {
        return (List<Disease>) diseaseRepository.findAll();
    }

    public void save(Disease disease) {
        diseaseRepository.save(disease);
    }

    public Disease get(Integer id) {
        return diseaseRepository.findByDiseaseID(id);
    }

    public void delete(Integer id) {
        diseaseRepository.deleteById(id);
    }
}
