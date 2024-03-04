package Repository;

import Domain.Disease;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Databasesql.*;

public class DiseaseRepository extends Databasesql implements RepositoryInterface<Disease> {

    @Override
    public void add(Disease newObject) {
        String sql = "INSERT INTO diseases(diseaseID, name) VALUES(?, ?);";

        try {
            PreparedStatement stmt = this.connection().prepareStatement(sql);
            stmt.setInt(1, newObject.getDiseaseID());
            stmt.setString(2, newObject.getName());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Disease deletedObject) {
        String sql = "DELETE FROM diseases WHERE diseaseID = ?;";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setInt(1, deletedObject.getDiseaseID());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Disease oldObject, Disease newObject) {
        String sql = "UPDATE diseases SET name=? WHERE diseaseID = ?;";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setString(1, newObject.getName());
            stmt.setInt(2, oldObject.getDiseaseID());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Disease> readAll() {
        String sql = "SELECT * FROM diseases;";

        try {
            Statement stmt = connection().createStatement();
            List<Disease> diseases = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                Disease disease = new Disease(
                        resultSet.getInt("diseaseID"),
                        resultSet.getString("name")
                );
                diseases.add(disease);
            }
            return (ArrayList<Disease>) diseases;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Disease findByIdentifier(ArrayList<String> identifier) {
        String sql = "SELECT * FROM diseases WHERE diseaseID = ?;";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(identifier.get(0)));
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return new Disease(
                        resultSet.getInt("diseaseID"),
                        resultSet.getString("name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
