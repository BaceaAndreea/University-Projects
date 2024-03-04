package Repository;

import Domain.Medication;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Databasesql.*;

public class MedicationRepository extends Databasesql implements RepositoryInterface<Medication> {

    @Override
    public void add(Medication newObject) {
        // Adăugare la bază de date
        String sql = "INSERT INTO medications(medicationID, name, administrationRoute, storageAmount, expirationDate) VALUES(?, ?, ?, ?, ?);";

        try {
            PreparedStatement stmt = this.connection().prepareStatement(sql);
            stmt.setInt(1, newObject.getMedicationID());
            stmt.setString(2, newObject.getName());
            stmt.setString(3, newObject.getAdministrationRoute());
            stmt.setInt(4, newObject.getStorageAmount());
            stmt.setString(5, newObject.getExpirationDate());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Medication deletedObject) {
        // Ștergere din bază de date
        String sql = "DELETE FROM medications WHERE medicationID = ?;";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setInt(1, deletedObject.getMedicationID());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Medication oldObject, Medication newObject) {
        // Actualizare în bază de date
        String sql = "UPDATE medications SET name=?, administrationRoute=?, storageAmount=?, expirationDate=? WHERE medicationID = ?;";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setString(1, newObject.getName());
            stmt.setString(2, newObject.getAdministrationRoute());
            stmt.setInt(3, newObject.getStorageAmount());
            stmt.setString(4, newObject.getExpirationDate());
            stmt.setInt(5, oldObject.getMedicationID());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Medication> readAll() {
        // Citire din bază de date
        String sql = "SELECT * FROM medications;";

        try {
            Statement stmt = connection().createStatement();
            List<Medication> medications = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                int medicationID = resultSet.getInt("medicationID");
                String name = resultSet.getString("name");
                String administrationRoute = resultSet.getString("administrationRoute");
                int storageAmount = resultSet.getInt("storageAmount");
                String expirationDate = resultSet.getString("expirationDate");

                Medication medication = new Medication(medicationID, name, administrationRoute, storageAmount, expirationDate);
                medications.add(medication);
            }
            return (ArrayList<Medication>) medications;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Medication findByIdentifier(ArrayList<String> identifier) {
        // Cautare în bază de date
        String sql = "SELECT * FROM medications WHERE medicationID = ?;";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(identifier.get(0)));
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int medicationID = resultSet.getInt("medicationID");
                String name = resultSet.getString("name");
                String administrationRoute = resultSet.getString("administrationRoute");
                int storageAmount = resultSet.getInt("storageAmount");
                String expirationDate = resultSet.getString("expirationDate");

                return new Medication(medicationID, name, administrationRoute, storageAmount, expirationDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
