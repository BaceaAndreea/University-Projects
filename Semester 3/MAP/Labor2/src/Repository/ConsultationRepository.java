package Repository;

import Domain.Consultation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Databasesql.*;

public class ConsultationRepository extends Databasesql implements RepositoryInterface<Consultation> {

    @Override
    public void add(Consultation newObject) {
        String sql = "INSERT INTO consultations(patientID, doctorID, date, diseaseID, price) VALUES(?, ?, ?, ?, ?);";

        try {
            PreparedStatement stmt = this.connection().prepareStatement(sql);
            stmt.setInt(1, newObject.getPatientID());
            stmt.setInt(2, newObject.getDoctorID());
            stmt.setString(3, newObject.getDate());
            stmt.setInt(4, newObject.getDiseaseID());
            stmt.setInt(5, newObject.getPrice());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Consultation deletedObject) {
        String sql = "DELETE FROM consultations WHERE patientID = ? AND doctorID = ? AND date = ?;";

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
    public void update(Consultation oldObject, Consultation newObject) {
        String sql = "UPDATE consultations SET diseaseID=?, price=? WHERE patientID = ? AND doctorID = ? AND date = ?;";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setInt(1, newObject.getDiseaseID());
            stmt.setInt(2, newObject.getPrice());
            stmt.setInt(3, oldObject.getPatientID());
            stmt.setInt(4, oldObject.getDoctorID());
            stmt.setString(5, oldObject.getDate());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Consultation> readAll() {
        String sql = "SELECT * FROM consultations;";

        try {
            Statement stmt = connection().createStatement();
            List<Consultation> consultations = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                Consultation consultation = new Consultation(
                        resultSet.getInt("patientID"),
                        resultSet.getInt("doctorID"),
                        resultSet.getString("date"),
                        resultSet.getInt("diseaseID"),
                        resultSet.getInt("price")
                );
                consultations.add(consultation);
            }
            return (ArrayList<Consultation>) consultations;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Consultation findByIdentifier(ArrayList<String> identifier) {
        String sql = "SELECT * FROM consultations WHERE patientID = ? AND doctorID = ? AND date = ?;";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(identifier.get(0)));
            stmt.setInt(2, Integer.parseInt(identifier.get(1)));
            stmt.setString(3, identifier.get(2));
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return new Consultation(
                        resultSet.getInt("patientID"),
                        resultSet.getInt("doctorID"),
                        resultSet.getString("date"),
                        resultSet.getInt("diseaseID"),
                        resultSet.getInt("price")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
