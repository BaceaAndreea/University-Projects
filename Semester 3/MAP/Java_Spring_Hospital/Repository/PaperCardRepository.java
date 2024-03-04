package map.project.demo.Repository;
import map.project.demo.Domain.PaperCard;
import org.springframework.data.repository.CrudRepository;

public interface PaperCardRepository extends CrudRepository<PaperCard, Integer> {

    public PaperCard findByWrittenID(int writtenID);

}
