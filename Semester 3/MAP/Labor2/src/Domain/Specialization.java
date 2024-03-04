package Domain;

public class Specialization {
    private int specializationID;
    private String name;

    public Specialization(int specializationID, String name) {
        this.specializationID = specializationID;
        this.name = name;
    }

    public int getSpecializationID() {
        return specializationID;
    }

    public void setSpecializationID(int specializationID) {
        this.specializationID = specializationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Specialization{" +
                "specializationID=" + specializationID +
                ", name='" + name + '\'' +
                '}';
    }
}