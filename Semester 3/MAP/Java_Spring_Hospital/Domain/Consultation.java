package map.project.demo.Domain;

import jakarta.persistence.*;
import lombok.*;
import map.project.demo.Observers.Observable;
import map.project.demo.Observers.Observer;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

@Table(name = "consultations")
public class Consultation{// implements Observable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int consultationID;
    private int patientID;
    private int doctorID;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private String date;
    private int diseaseID;
    private boolean card;
    private int price;


//    //private ArrayList<Observer> observers = new ArrayList<>();
//
//    @Override
//    public void addObserver(Observer observer) {
//        observers.add(observer);
//    }
//
//    @Override
//    public void removeObserver(Observer observer){
//        observers.remove(observer);
//    }
//
//    @Override
//    public void notifyObservers(){
//        for(Observer observer  : observers){
//            observer.updateObservers(this.patientID);
//        }
//    }
}
