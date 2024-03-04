package map.project.demo.Repository;
import map.project.demo.Domain.Hospital;
import org.springframework.data.repository.CrudRepository;

public interface HospitalRepository extends CrudRepository<Hospital, Integer> {

    public Hospital findByHospitalID(int hospitalID);

}
