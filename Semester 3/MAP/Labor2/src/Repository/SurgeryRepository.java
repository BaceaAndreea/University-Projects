package Repository;

import Domain.Surgery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Databasesql.*;


public class SurgeryRepository extends Databasesql implements RepositoryInterface<Surgery> {

    @Override
    public void add(Surgery newObject) {
        // Adăugare la bază de date
        String sql = "INSERT INTO surgeries(patientID, doctorID, date, diseaseID, name, medicationID) VALUES(?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement stmt = this.connection().prepareStatement(sql);
            stmt.setInt(1, newObject.getPatientID());
            stmt.setInt(2, newObject.getDoctorID());
            stmt.setString(3, newObject.getDate());
            stmt.setInt(4, newObject.getDiseaseID());
            stmt.setString(5, newObject.getName());
            stmt.setInt(6, newObject.getMedicationID());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Surgery deletedObject) {
        // Ștergere din bază de date
        String sql = "DELETE FROM surgeries WHERE patientID = ? AND doctorID = ? AND date = ?;";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setInt(1, deletedObject.getPatientID());
            stmt.setInt(2, deletedObject.getDoctorID());
            stmt.setString(3, deletedObject.getDate());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Surgery oldObject, Surgery newObject) {
        // Actualizare în bază de date
        String sql = "UPDATE surgeries SET diseaseID=?, name=?, medicationID=? WHERE patientID = ? AND doctorID = ? AND date = ?;";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setInt(1, newObject.getDiseaseID());
            stmt.setString(2, newObject.getName());
            stmt.setInt(3, newObject.getMedicationID());
            stmt.setInt(4, oldObject.getPatientID());
            stmt.setInt(5, oldObject.getDoctorID());
            stmt.setString(6, oldObject.getDate());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Surgery> readAll() {
        // Citire din bază de date
        String sql = "SELECT * FROM surgeries;";

        try {
            Statement stmt = connection().createStatement();
            List<Surgery> surgeries = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                int patientID = resultSet.getInt("patientID");
                int doctorID = resultSet.getInt("doctorID");
                String date = resultSet.getString("date");
                int diseaseID = resultSet.getInt("diseaseID");
                String name = resultSet.getString("name");
                int medicationID = resultSet.getInt("medicationID");

                Surgery surgery = new Surgery(patientID, doctorID, date, diseaseID, name, medicationID);
                surgeries.add(surgery);
            }
            return (ArrayList<Surgery>) surgeries;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Surgery findByIdentifier(ArrayList<String> identifier) {
        // Cautare în bază de date
        String sql = "SELECT * FROM surgeries WHERE patientID = ? AND doctorID = ? AND date = ?;";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(identifier.get(0)));
            stmt.setInt(2, Integer.parseInt(identifier.get(1)));
            stmt.setString(3, identifier.get(2));
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int patientID = resultSet.getInt("patientID");
                int doctorID = resultSet.getInt("doctorID");
                String date = resultSet.getString("date");
                int diseaseID = resultSet.getInt("diseaseID");
                String name = resultSet.getString("name");
                int medicationID = resultSet.getInt("medicationID");

                return new Surgery(patientID, doctorID, date, diseaseID, name, medicationID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
