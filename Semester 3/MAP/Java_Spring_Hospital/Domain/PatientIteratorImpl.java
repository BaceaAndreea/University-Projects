package map.project.demo.Domain;

import map.project.demo.Iterator.PatientIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class PatientIteratorImpl implements PatientIterator<Patient> {
    private final Iterable<Patient> patients;
    private Iterator<Patient> iterator ;
    private int index;

    public PatientIteratorImpl(Iterable<Patient> patients) {
        this.patients = patients;
        this.iterator = patients.iterator();
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Patient next() {
        if (this.hasNext()) {
            return iterator.next();
        }
        return null;
    }
}
