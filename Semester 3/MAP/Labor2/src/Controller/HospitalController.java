package Controller;

import Domain.Hospital;
import Repository.HospitalRepository;
import java.util.ArrayList;

public class HospitalController implements ControllerInterface<Hospital> {
    private final HospitalRepository hospitalRepository;
    public HospitalController(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }
    @Override
    public void add(ArrayList<String> newObjectData){
        Hospital newObject= new Hospital(Integer.parseInt(newObjectData.get(0)), newObjectData.get(1), Integer.parseInt(newObjectData.get(2)));
        hospitalRepository.add(newObject);
    }

    @Override
    public void delete(ArrayList<String> identifier) {
        if(hospitalRepository.findByIdentifier(identifier) != null) {
            hospitalRepository.delete(hospitalRepository.findByIdentifier(identifier));
        }
        else {
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }

    @Override
    public void update(ArrayList<String> identifier, ArrayList<String> newObjectData) {
        if(hospitalRepository.findByIdentifier(identifier) != null) {
            delete(identifier);
            add(newObjectData);
        }
        else{
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }

    @Override
    public ArrayList<Hospital> readAll(){
        return hospitalRepository.readAll();
    }
}