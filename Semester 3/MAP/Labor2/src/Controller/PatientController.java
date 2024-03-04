package Controller;

import Domain.Patient;
import Iterator.PatientIterator;
import Repository.PatientRepository;
import java.util.ArrayList;

public class PatientController implements ControllerInterface<Patient> {
    private final PatientRepository patientRepository;
    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    @Override
    public void add(ArrayList<String> newObjectData){
        Patient newObject= new Patient(Integer.parseInt(newObjectData.get(0)), newObjectData.get(1), newObjectData.get(2), newObjectData.get(3), newObjectData.get(4), Integer.parseInt(newObjectData.get(5)));
        patientRepository.add(newObject);
    }

    @Override
    public void delete(ArrayList<String> identifier) {
        if(patientRepository.findByIdentifier(identifier) != null) {
            patientRepository.delete(patientRepository.findByIdentifier(identifier));
        }
        else {
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }

    @Override
    public void update(ArrayList<String> identifier, ArrayList<String> newObjectData) {
        if(patientRepository.findByIdentifier(identifier) != null) {
            delete(identifier);
            add(newObjectData);
        }
        else{
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }

    @Override
    public ArrayList<Patient> readAll(){
        return patientRepository.readAll();
    }

    public void iteratePatients() {
        PatientIterator<Patient> iterator = patientRepository.createIterator();
        while (iterator.hasNext()) {
            Patient patient = iterator.next();
            System.out.println(patient);
        }
    }

}