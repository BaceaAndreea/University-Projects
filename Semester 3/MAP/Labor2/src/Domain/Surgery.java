package Domain;

public class Surgery {
    private int patientID, doctorID;
    private String date;
    private int diseaseID;
    private String name;
    private int medicationID;

    public Surgery(int patientID, int doctorID, String date, int diseaseID, String name, int medicationID) {
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.date = date;
        this.diseaseID = diseaseID;
        this.name = name;
        this.medicationID = medicationID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public int getMedicationID() {
        return medicationID;
    }

    public void setMedicationID(int medicationID) {
        this.medicationID = medicationID;
    }

    @Override
    public String toString() {
        return "Surgery{" +
                "patientID=" + patientID +
                ", doctorID=" + doctorID +
                ", date='" + date + '\'' +
                ", diseaseID=" + diseaseID +
                ", name='" + name + '\'' +
                ", medicationID=" + medicationID +
                '}';
    }
}