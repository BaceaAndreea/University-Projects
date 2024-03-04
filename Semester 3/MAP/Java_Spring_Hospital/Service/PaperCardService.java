package map.project.demo.Service;

import map.project.demo.Domain.PaperCard;
import map.project.demo.Repository.PaperCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperCardService {
    @Autowired
    private PaperCardRepository paperCardRepository;

    public List<PaperCard> listAll() {
        return (List<PaperCard>) paperCardRepository.findAll();
    }

    public void save(PaperCard paperCard) {
        paperCardRepository.save(paperCard);
    }

    public PaperCard get(Integer paperCardID) {
        return paperCardRepository.findByWrittenID(paperCardID);
    }

    public void delete(Integer paperCardID) {
        paperCardRepository.deleteById(paperCardID);
    }
}
