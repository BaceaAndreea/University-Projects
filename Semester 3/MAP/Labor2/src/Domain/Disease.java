package Domain;

public class Disease {
    private int diseaseID;
    private String name;
    public Disease(int diseaseID, String name) {
        this.diseaseID = diseaseID;
        this.name = name;
    }

    public int getDiseaseID() {
        return diseaseID;
    }

    public void setDiseaseID(int diseaseID) {
        this.diseaseID = diseaseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Disease{" +
                "diseaseID=" + diseaseID +
                ", name='" + name + '\'' +
                '}';
    }
}