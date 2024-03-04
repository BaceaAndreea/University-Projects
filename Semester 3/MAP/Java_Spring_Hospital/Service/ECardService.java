package map.project.demo.Service;

import map.project.demo.Domain.ECard;
import map.project.demo.Repository.ECardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ECardService {
    @Autowired
    private ECardRepository eCardRepository;

    public List<ECard> listAll() {
        return (List<ECard>) eCardRepository.findAll();
    }

    public void save(ECard eCard) {
        eCardRepository.save(eCard);
    }

    public ECard get(Integer eCardID) {
        return eCardRepository.findByElectronicID(eCardID);
    }

    public void delete(Integer eCardID) {
        eCardRepository.deleteById(eCardID);
    }
}
