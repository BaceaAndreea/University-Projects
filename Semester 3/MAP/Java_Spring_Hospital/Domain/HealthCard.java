package map.project.demo.Domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class HealthCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private String expirationDate;
    private int pin;
}