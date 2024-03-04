package Domain;

public class HealthCard {
    private String expirationDate;
    private int pin;

    public HealthCard(String expirationDate, int pin) {
        this.expirationDate = expirationDate;
        this.pin = pin;
    }
    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "ElectronicCard{" +
                "expirationDate='" + expirationDate + '\'' +
                ", pin=" + pin +
                '}';
    }
}