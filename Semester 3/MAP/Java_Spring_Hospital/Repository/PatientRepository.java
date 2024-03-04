package map.project.demo.Repository;
import map.project.demo.Domain.Patient;
import map.project.demo.Iterator.PatientIterator;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Integer> {

    public Patient findByPatientID(int patientID);

}
