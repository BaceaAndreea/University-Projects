package Repository;

import Domain.Doctor;

import Databasesql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepository extends Databasesql implements RepositoryInterface<Doctor> {

    @Override
    public void add(Doctor newObject) {
        String sql = "INSERT INTO doctors(doctorID, name, firstName, birthdate, hospitalID, contactPhone, specializationID, cabinetID) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement stmt = this.connection().prepareStatement(sql);
            stmt.setInt(1, newObject.getDoctorID());
            stmt.setString(2, newObject.getName());
            stmt.setString(3, newObject.getFirstName());
            stmt.setString(4, newObject.getBirthdate());
            stmt.setInt(5, newObject.getHospitalID());
            stmt.setString(6, newObject.getContactPhone());
            stmt.setInt(7, newObject.getSpecializationID());
            stmt.setInt(8, newObject.getCabinetID());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Doctor deletedObject) {
        String sql = "DELETE FROM doctors WHERE doctorID = ?;";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setInt(1, deletedObject.getDoctorID());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Doctor oldObject, Doctor newObject) {
        String sql = "UPDATE doctors SET name=?, firstName=?, birthdate=?, hospitalID=?, contactPhone=?, specializationID=?, cabinetID=? WHERE doctorID = ?;";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setString(1, newObject.getName());
            stmt.setString(2, newObject.getFirstName());
            stmt.setString(3, newObject.getBirthdate());
            stmt.setInt(4, newObject.getHospitalID());
            stmt.setString(5, newObject.getContactPhone());
            stmt.setInt(6, newObject.getSpecializationID());
            stmt.setInt(7, newObject.getCabinetID());
            stmt.setInt(8, oldObject.getDoctorID());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Doctor> readAll() {
        String sql = "SELECT * FROM doctors;";

        try {
            Statement stmt = connection().createStatement();
            List<Doctor> doctors = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                Doctor doctor = new Doctor.Builder(resultSet.getInt("doctorID"), resultSet.getString("name"), resultSet.getString("firstName"))
                        .birthdate(resultSet.getString("birthdate"))
                        .hospitalID(resultSet.getInt("hospitalID"))
                        .contactPhone(resultSet.getString("contactPhone"))
                        .specializationID(resultSet.getInt("specializationID"))
                        .cabinetID(resultSet.getInt("cabinetID"))
                        .build();
                doctors.add(doctor);
            }
            return (ArrayList<Doctor>) doctors;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Doctor findByIdentifier(ArrayList<String> identifier) {
        String sql = "SELECT * FROM doctors WHERE doctorID = ?;";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(identifier.get(0)));
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return new Doctor.Builder(resultSet.getInt("doctorID"), resultSet.getString("name"), resultSet.getString("firstName"))
                        .birthdate(resultSet.getString("birthdate"))
                        .hospitalID(resultSet.getInt("hospitalID"))
                        .contactPhone(resultSet.getString("contactPhone"))
                        .specializationID(resultSet.getInt("specializationID"))
                        .cabinetID(resultSet.getInt("cabinetID"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
