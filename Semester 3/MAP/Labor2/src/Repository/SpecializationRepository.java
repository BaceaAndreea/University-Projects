package Repository;

import Domain.Specialization;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Databasesql.*;


public class SpecializationRepository extends Databasesql implements RepositoryInterface<Specialization> {

    @Override
    public void add(Specialization newObject) {
        // Adăugare la bază de date
        String sql = "INSERT INTO specializations(specializationID, name) VALUES(?, ?);";

        try {
            PreparedStatement stmt = this.connection().prepareStatement(sql);
            stmt.setInt(1, newObject.getSpecializationID());
            stmt.setString(2, newObject.getName());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Specialization deletedObject) {
        // Ștergere din bază de date
        String sql = "DELETE FROM specializations WHERE specializationID = ?;";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setInt(1, deletedObject.getSpecializationID());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Specialization oldObject, Specialization newObject) {
        // Actualizare în bază de date
        String sql = "UPDATE specializations SET name=? WHERE specializationID = ?;";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setString(1, newObject.getName());
            stmt.setInt(2, oldObject.getSpecializationID());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Specialization> readAll() {
        // Citire din bază de date
        String sql = "SELECT * FROM specializations;";

        try {
            Statement stmt = connection().createStatement();
            List<Specialization> specializations = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                int specializationID = resultSet.getInt("specializationID");
                String name = resultSet.getString("name");

                Specialization specialization = new Specialization(specializationID, name);
                specializations.add(specialization);
            }
            return (ArrayList<Specialization>) specializations;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Specialization findByIdentifier(ArrayList<String> identifier) {
        // Cautare în bază de date
        String sql = "SELECT * FROM specializations WHERE specializationID = ?;";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(identifier.get(0)));
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int specializationID = resultSet.getInt("specializationID");
                String name = resultSet.getString("name");

                return new Specialization(specializationID, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
