package map.project.demo.Domain;
import jakarta.persistence.*;
import lombok.*;
import map.project.demo.Iterator.PatientIterator;
import map.project.demo.Observers.Observer;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString

@Table (name = "patients")
public class Patient{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientID;
    private String name;
    private String firstName;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private String birthdate;
    private int cardID;

    public Patient(String name, String firstName, String birthdate, int cardID) {
        this.name = name;
        this.firstName = firstName;
        this.birthdate = birthdate;
        this.cardID = cardID;
    }
}