package map.project.demo.Service;

import map.project.demo.Domain.Surgery;
import map.project.demo.Repository.SurgeryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurgeryService {
    @Autowired
    private SurgeryRepository surgeryRepository;

    public List<Surgery> listAll() {
        return (List<Surgery>) surgeryRepository.findAll();
    }

    public void save(Surgery surgery) {
        surgeryRepository.save(surgery);
    }

    public Surgery get(Integer id) {
        return surgeryRepository.findBySurgeryID(id);
    }

    public void delete(Integer id) {
        surgeryRepository.deleteById(id);
    }
}
