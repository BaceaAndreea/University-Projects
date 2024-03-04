package Controller;

import Domain.Surgery;
import Repository.SurgeryRepository;
import java.util.ArrayList;

public class SurgeryController implements ControllerInterface<Surgery> {
    private final SurgeryRepository surgeryRepository;
    public SurgeryController(SurgeryRepository surgeryRepository) {
        this.surgeryRepository = surgeryRepository;
    }
    @Override
    public void add(ArrayList<String> newObjectData){
        Surgery newObject= new Surgery(Integer.parseInt(newObjectData.get(0)), Integer.parseInt(newObjectData.get(1)), newObjectData.get(2), Integer.parseInt(newObjectData.get(3)), newObjectData.get(4), Integer.parseInt(newObjectData.get(5)));
        surgeryRepository.add(newObject);
    }

    @Override
    public void delete(ArrayList<String> identifier) {
        if(surgeryRepository.findByIdentifier(identifier) != null) {
            surgeryRepository.delete(surgeryRepository.findByIdentifier(identifier));
        }
        else {
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }

    @Override
    public void update(ArrayList<String> identifier, ArrayList<String> newObjectData) {
        if(surgeryRepository.findByIdentifier(identifier) != null) {
            delete(identifier);
            add(newObjectData);
        }
        else{
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }

    @Override
    public ArrayList<Surgery> readAll(){
        return surgeryRepository.readAll();
    }
}