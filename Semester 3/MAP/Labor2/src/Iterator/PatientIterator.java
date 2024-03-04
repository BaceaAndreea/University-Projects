package Iterator;

import Domain.Patient;
public interface PatientIterator<T> {
    boolean hasNext();
    T next();
}
