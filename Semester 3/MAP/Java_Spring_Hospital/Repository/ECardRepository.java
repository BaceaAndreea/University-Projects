package map.project.demo.Repository;
import map.project.demo.Domain.ECard;
import org.springframework.data.repository.CrudRepository;

public interface ECardRepository extends CrudRepository<ECard, Integer> {

    public ECard findByElectronicID(int electronicID);

}
