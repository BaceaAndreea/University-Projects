package map.project.demo.Domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
@Table(name = "cabinets")
public class Cabinet {
    public Cabinet(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private int cabinetID;
    private String name;
}