package map.project.demo.Service;

import map.project.demo.Domain.Cabinet;

import map.project.demo.Repository.CabinetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CabinetService {
    @Autowired
    private CabinetRepository cabinetRepository;
    @Autowired
    public CabinetService(CabinetRepository cabinetRepository) {
        this.cabinetRepository = cabinetRepository;
    }

    public List<Cabinet> listAll() {
        return (List<Cabinet>) cabinetRepository.findAll();
    }

    public void save(Cabinet cabinet) {
        cabinetRepository.save(cabinet);
    }

    public Cabinet get(Integer cabinetID) {
        return cabinetRepository.findByCabinetID(cabinetID);
    }

    public void delete(Integer cabinetID) {
        cabinetRepository.deleteById(cabinetID);
    }
}
