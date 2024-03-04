package map.project.demo.Repository;
import map.project.demo.Domain.Disease;
import org.springframework.data.repository.CrudRepository;

public interface DiseaseRepository extends CrudRepository<Disease, Integer> {

    public Disease findByDiseaseID(int diseaseID);

}
