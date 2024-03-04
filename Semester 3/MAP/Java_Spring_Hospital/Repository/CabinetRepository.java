package map.project.demo.Repository;
import map.project.demo.Domain.Cabinet;
import org.springframework.data.repository.CrudRepository;

public interface CabinetRepository extends CrudRepository<Cabinet, Integer> {

    public Cabinet findByCabinetID(int cabinetID);

}
