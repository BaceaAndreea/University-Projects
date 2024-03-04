package map.project.demo.Factory;

import map.project.demo.Domain.ECard;
import map.project.demo.Domain.HealthCard;

import java.util.ArrayList;

public class ECardFactory implements HealthCardFactory<ECard> {
    @Override
    public ECard create(String expirationDate,int pin){
        return new ECard(expirationDate, pin);
    }

}
