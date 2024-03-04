package map.project.demo.Service;

import map.project.demo.Domain.Consultation;
import map.project.demo.Repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationService {
    @Autowired
    private ConsultationRepository consultationRepository;

    public List<Consultation> listAll() {
        return (List<Consultation>) consultationRepository.findAll();
    }

    public void save(Consultation consultation) {
        consultationRepository.save(consultation);
    }

    public Consultation get(Integer consultationID) {
        return consultationRepository.findByConsultationID(consultationID);
    }

    public void delete(Integer consultationID) {
        consultationRepository.deleteById(consultationID);
    }
}
