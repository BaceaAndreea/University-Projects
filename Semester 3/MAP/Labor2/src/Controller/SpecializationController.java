package Controller;

import Domain.Specialization;
import Repository.SpecializationRepository;
import java.util.ArrayList;

public class SpecializationController implements ControllerInterface<Specialization> {
    private final SpecializationRepository specializationRepository;
    public SpecializationController(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }
    @Override
    public void add(ArrayList<String> newObjectData){
        Specialization newObject= new Specialization(Integer.parseInt(newObjectData.get(0)), newObjectData.get(1));
        specializationRepository.add(newObject);
    }

    @Override
    public void delete(ArrayList<String> identifier) {
        if(specializationRepository.findByIdentifier(identifier) != null) {
            specializationRepository.delete(specializationRepository.findByIdentifier(identifier));
        }
        else {
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }

    @Override
    public void update(ArrayList<String> identifier, ArrayList<String> newObjectData) {
        if(specializationRepository.findByIdentifier(identifier) != null) {
            delete(identifier);
            add(newObjectData);
        }
        else{
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }

    @Override
    public ArrayList<Specialization> readAll(){
        return specializationRepository.readAll();
    }
}