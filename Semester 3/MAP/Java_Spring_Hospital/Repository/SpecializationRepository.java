package map.project.demo.Repository;
import map.project.demo.Domain.Specialization;
import org.springframework.data.repository.CrudRepository;

public interface SpecializationRepository extends CrudRepository<Specialization, Integer> {

    public Specialization findBySpecializationID(int specializationID);

}
