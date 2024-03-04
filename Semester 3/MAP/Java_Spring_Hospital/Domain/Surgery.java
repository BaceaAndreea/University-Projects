package map.project.demo.Domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString

@Table (name = "surgeries")
public class Surgery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int surgeryID;
    private int patientID, doctorID;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private String date;
    private int diseaseID;
    private String name;
    private int medicationID;

}