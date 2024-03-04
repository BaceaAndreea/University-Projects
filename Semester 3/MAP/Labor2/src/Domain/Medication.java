package Domain;

public class Medication {
    private int medicationID;
    private String name;
    private String administrationRoute;
    private int storageAmount;
    private String expirationDate;

    public Medication(int medicationID, String name, String administrationRoute, int storageAmount, String expirationDate) {
        this.medicationID = medicationID;
        this.name = name;
        this.administrationRoute = administrationRoute;
        this.storageAmount = storageAmount;
        this.expirationDate = expirationDate;
    }

    public int getMedicationID() {
        return medicationID;
    }

    public void setMedicationID(int medicationID) {
        this.medicationID = medicationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdministrationRoute() {
        return administrationRoute;
    }

    public void setAdministrationRoute(String administrationRoute) {
        this.administrationRoute = administrationRoute;
    }

    public int getStorageAmount() {
        return storageAmount;
    }

    public void setStorageAmount(int storageAmount) {
        this.storageAmount = storageAmount;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Medication{" +
                "medicationID=" + medicationID +
                ", name='" + name + '\'' +
                ", administrationRoute='" + administrationRoute + '\'' +
                ", storageAmount=" + storageAmount +
                ", expirationDate='" + expirationDate + '\'' +
                '}';
    }
}
