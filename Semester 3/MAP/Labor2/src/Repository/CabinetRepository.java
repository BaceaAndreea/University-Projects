package Repository;

import Domain.Cabinet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Databasesql.*;

public class CabinetRepository extends Databasesql implements RepositoryInterface<Cabinet> {

    @Override
    public void add(Cabinet newObject) {
        String sql = "INSERT INTO cabinets(cabinetID, name) VALUES(?, ?);";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setInt(1, newObject.getCabinetID());
            stmt.setString(2, newObject.getName());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Cabinet deletedObject) {
        String sql = "DELETE FROM cabinets WHERE cabinetID = ?;";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setInt(1, deletedObject.getCabinetID());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Cabinet oldObject, Cabinet newObject) {
        String sql = "UPDATE cabinets SET name=? WHERE cabinetID = ?;";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setString(1, newObject.getName());
            stmt.setInt(2, oldObject.getCabinetID());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Cabinet> readAll() {
        String sql = "SELECT * FROM cabinets;";

        try {
            Statement stmt = connection().createStatement();
            List<Cabinet> cabinets = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                Cabinet cabinet = new Cabinet(
                        resultSet.getInt("cabinetID"),
                        resultSet.getString("name")
                );
                cabinets.add(cabinet);
            }
            return (ArrayList<Cabinet>) cabinets;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Cabinet findByIdentifier(ArrayList<String> identifier) {
        String sql = "SELECT * FROM cabinets WHERE cabinetID = ?;";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(identifier.get(0)));
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return new Cabinet(
                        resultSet.getInt("cabinetID"),
                        resultSet.getString("name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
