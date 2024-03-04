package Controller;

import Domain.Doctor;
import Repository.DoctorRepository;
import java.util.ArrayList;

public class DoctorController implements ControllerInterface<Doctor> {
    private final DoctorRepository doctorRepository;
    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
    @Override
    public void add(ArrayList<String> newObjectData){
        Doctor newObject = new Doctor.Builder(Integer.parseInt(newObjectData.get(0)), newObjectData.get(1), newObjectData.get(2))
                .birthdate(newObjectData.get(3))
                .hospitalID(Integer.parseInt(newObjectData.get(4)))
                .contactPhone(newObjectData.get(5))
                .specializationID(Integer.parseInt(newObjectData.get(6)))
                .cabinetID(Integer.parseInt( newObjectData.get(7)))
                .build();

        doctorRepository.add(newObject);
    }

    @Override
    public void delete(ArrayList<String> identifier) {
        if(doctorRepository.findByIdentifier(identifier) != null) {
            doctorRepository.delete(doctorRepository.findByIdentifier(identifier));
        }
        else {
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }

    @Override
    public void update(ArrayList<String> identifier, ArrayList<String> newObjectData) {
        if(doctorRepository.findByIdentifier(identifier) != null) {
            delete(identifier);
            add(newObjectData);
        }
        else{
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }

    @Override
    public ArrayList<Doctor> readAll(){
        return doctorRepository.readAll();
    }
}
