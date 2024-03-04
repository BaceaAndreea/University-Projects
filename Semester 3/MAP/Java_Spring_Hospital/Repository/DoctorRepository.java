package map.project.demo.Repository;
import map.project.demo.Domain.Doctor;
import org.springframework.data.repository.CrudRepository;


public interface DoctorRepository extends CrudRepository<Doctor, Integer> {

    public Doctor findByDoctorID(int doctorID);

}
