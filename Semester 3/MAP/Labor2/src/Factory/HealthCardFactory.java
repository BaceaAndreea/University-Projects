package Factory;

import Domain.HealthCard;

import java.util.ArrayList;

public interface HealthCardFactory<T extends HealthCard> {
    T create(ArrayList<String> cardData);
}
