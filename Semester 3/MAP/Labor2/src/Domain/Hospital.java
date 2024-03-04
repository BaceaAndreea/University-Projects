package Domain;

public class Hospital {
    private int hospitalID;
    private String name;
    private int capacity;

    public Hospital(int hospitalID, String name, int capacity) {
        this.hospitalID = hospitalID;
        this.name = name;
        this.capacity = capacity;
    }

    public int getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(int hospitalID) {
        this.hospitalID = hospitalID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "hospitalID=" + hospitalID +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}