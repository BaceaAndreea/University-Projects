package Domain;

import Observers.Observable;
import Observers.Observer;

import java.util.ArrayList;

public class Consultation implements Observable {
    private int patientID;
    private int doctorID;
    private String date;
    private int diseaseID;
    private boolean card;
    private int price;
    private ArrayList<Observer> observers;

    public Consultation(int patientID, int doctorID, String date, int diseaseID, int price) {
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.date = date;
        this.diseaseID = diseaseID;
        this.price = price;
        this.observers= new ArrayList<>();
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDiseaseID() {
        return diseaseID;
    }

    public void setDiseaseID(int diseaseID) {
        this.diseaseID = diseaseID;
    }

    public boolean isCard() {
        return card;
    }

    public void setCard(boolean card) {
        this.card = card;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Consultation{" +
                "patientID=" + patientID +
                ", doctorID=" + doctorID +
                ", date='" + date + '\'' +
                ", diseaseID=" + diseaseID +
                ", card=" + card +
                ", price=" + price +
                '}';
    }


    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer){
        observers.remove(observer);
    }
    @Override
    public void notifyObservers(){
        for(Observer observer  : observers){
            observer.updateObservers(this.patientID);
        }
    }
}
