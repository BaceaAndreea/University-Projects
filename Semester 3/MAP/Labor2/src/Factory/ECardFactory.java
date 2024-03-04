package Factory;

import Domain.ECard;
import Domain.HealthCard;

import java.util.ArrayList;

public class ECardFactory implements HealthCardFactory<ECard> {
    @Override
    public ECard create(ArrayList<String> cardData) {
        return new ECard(cardData.get(0), Integer.parseInt(cardData.get(1)), Integer.parseInt(cardData.get(2)));
    }

}
