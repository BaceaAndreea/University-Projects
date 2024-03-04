package Controller;

import Domain.Medication;
import Repository.MedicationRepository;
import java.util.ArrayList;

public class MedicationController implements ControllerInterface<Medication> {
    private final MedicationRepository medicationRepository;
    public MedicationController(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }
    @Override
    public void add(ArrayList<String> newObjectData){
        Medication newObject= new Medication(Integer.parseInt(newObjectData.get(0)), newObjectData.get(1), newObjectData.get(2), Integer.parseInt(newObjectData.get(3)), newObjectData.get(4));
        medicationRepository.add(newObject);
    }

    @Override
    public void delete(ArrayList<String> identifier) {
        if(medicationRepository.findByIdentifier(identifier) != null) {
            medicationRepository.delete(medicationRepository.findByIdentifier(identifier));
        }
        else {
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }

    @Override
    public void update(ArrayList<String> identifier, ArrayList<String> newObjectData) {
        if(medicationRepository.findByIdentifier(identifier) != null) {
            delete(identifier);
            add(newObjectData);
        }
        else{
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }

    @Override
    public ArrayList<Medication> readAll(){
        return medicationRepository.readAll();
    }
}