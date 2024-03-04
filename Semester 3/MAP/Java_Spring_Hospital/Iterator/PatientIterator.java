package map.project.demo.Iterator;

import map.project.demo.Domain.Patient;

public interface PatientIterator<T> {
    boolean hasNext();
    T next();
}
