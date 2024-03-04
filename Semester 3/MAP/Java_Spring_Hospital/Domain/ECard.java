package map.project.demo.Domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Table(name="ECards")
public class ECard extends HealthCard{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int electronicID;

    public ECard(String expirationDate, int pin) {
        super(expirationDate, pin);
    }

    @Override
    public String toString() {
        return "ECard{" +
                "electronicID=" + electronicID +
                "expirationDate=" + getExpirationDate() +
                "pin=" + getPin() +
                '}';
    }
}