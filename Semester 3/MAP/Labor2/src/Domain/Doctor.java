package Domain;

public class Doctor {
    private int doctorID;
    private String name, firstName;
    private String birthdate;
    private int hospitalID;
    private String contactPhone;
    private int specializationID;
    private int cabinetID;

    private Doctor(Builder builder) {
        this.doctorID = builder.doctorID;
        this.name = builder.name;
        this.firstName = builder.firstName;
        this.birthdate = builder.birthdate;
        this.hospitalID = builder.hospitalID;
        this.contactPhone = builder.contactPhone;
        this.specializationID = builder.specializationID;
        this.cabinetID = builder.cabinetID;
    }

    public int getDoctorID(){
        return this.doctorID;
    }

    public static class Builder {

        private int doctorID;
        private String name, firstName;
        private String birthdate;
        private int hospitalID;
        private String contactPhone;
        private int specializationID;
        private int cabinetID;

        public Builder(int doctorID, String name, String firstName) {
            this.doctorID = doctorID;
            this.name = name;
            this.firstName = firstName;
        }

        public Builder birthdate(String birthdate) {
            this.birthdate = birthdate;
            return this;
        }

        public Builder hospitalID(int hospitalID) {
            this.hospitalID = hospitalID;
            return this;
        }

        public Builder contactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
            return this;
        }

        public Builder specializationID(int specializationID) {
            this.specializationID = specializationID;
            return this;
        }

        public Builder cabinetID(int cabinetID) {
            this.cabinetID = cabinetID;
            return this;
        }

        public Doctor build() {
            return new Doctor(this);
        }
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorID=" + doctorID +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", hospitalID=" + hospitalID +
                ", contactPhone='" + contactPhone + '\'' +
                ", specializationID=" + specializationID +
                ", cabinetID=" + cabinetID +
                '}';
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(int hospitalID) {
        this.hospitalID = hospitalID;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public int getSpecializationID() {
        return specializationID;
    }

    public void setSpecializationID(int specializationID) {
        this.specializationID = specializationID;
    }

    public int getCabinetID() {
        return cabinetID;
    }

    public void setCabinetID(int cabinetID) {
        this.cabinetID = cabinetID;
    }
}
