package Repository;

import Domain.Patient;

import Databasesql.*;
import Domain.PatientIteratorImpl;
import Iterator.PatientIterator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PatientRepository extends Databasesql implements RepositoryInterface<Patient> {

    @Override
    public void add(Patient newObject) {
        String sql = "INSERT INTO patients(patientID, name, firstName, birthdate, contactPhone, cardID) VALUES(?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setInt(1, newObject.getPatientID());
            stmt.setString(2, newObject.getName());
            stmt.setString(3, newObject.getFirstName());
            stmt.setString(4, newObject.getBirthdate());
            stmt.setString(5, newObject.getContactPhone());
            stmt.setInt(6, newObject.getCardID());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Patient deletedObject) {
        String sql = "DELETE FROM patients WHERE patientID = ?;";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setInt(1, deletedObject.getPatientID());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Patient oldObject, Patient newObject) {
        String sql = "UPDATE patients SET name=?, firstName=?, birthdate=?, contactPhone=?, cardID=? WHERE patientID = ?;";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setString(1, newObject.getName());
            stmt.setString(2, newObject.getFirstName());
            stmt.setString(3, newObject.getBirthdate());
            stmt.setString(4, newObject.getContactPhone());
            stmt.setInt(5, newObject.getCardID());
            stmt.setInt(6, oldObject.getPatientID());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Patient> readAll() {
        String sql = "SELECT * FROM patients;";

        try {
            Statement stmt = connection().createStatement();
            List<Patient> patients = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                Patient patient = new Patient(
                        resultSet.getInt("patientID"),
                        resultSet.getString("name"),
                        resultSet.getString("firstName"),
                        resultSet.getString("birthdate"),
                        resultSet.getString("contactPhone"),
                        resultSet.getInt("cardID")
                );
                patients.add(patient);
            }
            return (ArrayList<Patient>) patients;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Patient findByIdentifier(ArrayList<String> identifier) {
        String sql = "SELECT * FROM patients WHERE patientID = ?;";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(identifier.get(0)));
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return new Patient(
                        resultSet.getInt("patientID"),
                        resultSet.getString("name"),
                        resultSet.getString("firstName"),
                        resultSet.getString("birthdate"),
                        resultSet.getString("contactPhone"),
                        resultSet.getInt("cardID")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Metoda pentru crearea iteratorului
    public PatientIterator createIterator() {
        ArrayList<Patient> patients = readAll();
        PatientIteratorImpl iterator = new PatientIteratorImpl(patients);
        return iterator;
    }

}
