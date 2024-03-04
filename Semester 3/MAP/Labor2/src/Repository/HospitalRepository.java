package Repository;

import Domain.Hospital;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Databasesql.*;

public class HospitalRepository extends Databasesql implements RepositoryInterface<Hospital> {

    @Override
    public void add(Hospital newObject) {
        // Adăugare la bază de date
        String sql = "INSERT INTO hospitals(hospitalID, name, capacity) VALUES(?, ?, ?);";

        try {
            PreparedStatement stmt = this.connection().prepareStatement(sql);
            stmt.setInt(1, newObject.getHospitalID());
            stmt.setString(2, newObject.getName());
            stmt.setInt(3, newObject.getCapacity());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Hospital deletedObject) {
        // Ștergere din bază de date
        String sql = "DELETE FROM hospitals WHERE hospitalID = ?;";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setInt(1, deletedObject.getHospitalID());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Hospital oldObject, Hospital newObject) {
        // Actualizare în bază de date
        String sql = "UPDATE hospitals SET name=?, capacity=? WHERE hospitalID = ?;";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setString(1, newObject.getName());
            stmt.setInt(2, newObject.getCapacity());
            stmt.setInt(3, oldObject.getHospitalID());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Hospital> readAll() {
        // Citire din bază de date
        String sql = "SELECT * FROM hospitals;";

        try {
            Statement stmt = connection().createStatement();
            List<Hospital> hospitals = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                int hospitalID = resultSet.getInt("hospitalID");
                String name = resultSet.getString("name");
                int capacity = resultSet.getInt("capacity");

                Hospital hospital = new Hospital(hospitalID, name, capacity);
                hospitals.add(hospital);
            }
            return (ArrayList<Hospital>) hospitals;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Hospital findByIdentifier(ArrayList<String> identifier) {
        // Cautare în bază de date
        String sql = "SELECT * FROM hospitals WHERE hospitalID = ?;";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(identifier.get(0)));
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int hospitalID = resultSet.getInt("hospitalID");
                String name = resultSet.getString("name");
                int capacity = resultSet.getInt("capacity");

                return new Hospital(hospitalID, name, capacity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
