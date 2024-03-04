package Domain;

public class Cabinet {
    private int cabinetID;
    private String name;

    public Cabinet(int cabinetID, String name) {
        this.cabinetID = cabinetID;
        this.name = name;
    }

    public int getCabinetID() {
        return cabinetID;
    }

    public void setCabinetID(int cabinetID) {
        this.cabinetID = cabinetID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cabinet{" +
                "cabinetID=" + cabinetID +
                ", name='" + name + '\'' +
                '}';
    }
}