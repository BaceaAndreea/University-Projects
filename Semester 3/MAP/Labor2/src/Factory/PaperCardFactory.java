package Factory;

import Domain.HealthCard;
import Domain.PaperCard;

import java.util.ArrayList;

public class PaperCardFactory implements HealthCardFactory<PaperCard> {
    @Override
    public PaperCard create(ArrayList<String> cardData) {
        return new PaperCard(cardData.get(0), Integer.parseInt(cardData.get(1)), Integer.parseInt(cardData.get(2)));
    }
}
