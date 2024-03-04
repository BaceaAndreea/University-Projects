package Domain;

import Iterator.PatientIterator;

import java.util.ArrayList;


public class PatientIteratorImpl implements PatientIterator<Patient> {
    private ArrayList<Patient> patients;
    private int index;

    public PatientIteratorImpl(ArrayList<Patient> patients) {
        this.patients = patients;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < patients.size();
    }

    @Override
    public Patient next() {
        if (this.hasNext()) {
            return patients.get(index++);
        }
        return null;
    }
}
