package map.project.demo.Factory;

import map.project.demo.Domain.HealthCard;

import java.util.ArrayList;

public interface HealthCardFactory<T extends HealthCard> {
    T create(String expirationDate,int pin);
}
