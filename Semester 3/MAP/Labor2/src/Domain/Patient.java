package Domain;
import Iterator.PatientIterator;
import Observers.Observer;

import java.util.ArrayList;

public class Patient{
    private int patientID;
    private String name;
    private String firstName;
    private String birthdate;
    private String contactPhone;
    private int cardID;

    public Patient(int patientID, String name, String firstName, String birthdate, String contactPhone, int cardID) {
        this.patientID = patientID;
        this.name = name;
        this.firstName = firstName;
        this.birthdate = birthdate;
        this.contactPhone = contactPhone;
        this.cardID = cardID;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientID=" + patientID +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", cardID=" + cardID +
                '}';
    }

}