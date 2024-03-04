package map.project.demo.Repository;
import map.project.demo.Domain.Consultation;
import org.springframework.data.repository.CrudRepository;

public interface ConsultationRepository extends CrudRepository<Consultation, Integer> {

    public Consultation findByConsultationID(Integer consultationID);
}
