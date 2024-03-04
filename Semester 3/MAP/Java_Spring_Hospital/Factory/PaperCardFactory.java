package map.project.demo.Factory;

import map.project.demo.Domain.HealthCard;
import map.project.demo.Domain.PaperCard;

import java.util.ArrayList;

public class PaperCardFactory implements HealthCardFactory<PaperCard> {
    @Override
    public PaperCard create(String expirationDate,int pin) {
        return new PaperCard(expirationDate, pin);
    }
}
