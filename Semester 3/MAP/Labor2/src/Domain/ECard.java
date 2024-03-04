package Domain;

public class ECard extends HealthCard{
    private int electronicID;

    public ECard(String expirationDate, int pin, int electronicID) {
        super(expirationDate, pin);
        this.electronicID = electronicID;
    }

    public int getElectronicID() {
        return electronicID;
    }

    public void setCardID(int cardID) {
        this.electronicID = cardID;
    }

    @Override
    public String toString() {
        return "ECard{" +
                "electronicID=" + electronicID +
                '}';
    }
}