package Repository;

import Databasesql.Databasesql;
import Domain.ECard;
import Domain.PaperCard;
import Domain.HealthCard;
import Factory.ECardFactory;
import Factory.HealthCardFactory;
import Factory.PaperCardFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HealthCardRepository extends Databasesql implements RepositoryInterface<HealthCard> {
    private final ArrayList<HealthCard> healthCards = new ArrayList<>();
    private final HealthCardFactory<ECard> eCardFactory ;
    private final HealthCardFactory<PaperCard> paperCardFactory;
    private static HealthCardRepository instance;
    private HealthCardRepository(HealthCardFactory<ECard> eCardFactory, HealthCardFactory<PaperCard> paperCardFactory) {
        this.eCardFactory = eCardFactory;
        this.paperCardFactory = paperCardFactory;
    }

    // Metodă publică pentru a obține instanța unică
    public static HealthCardRepository getInstance(HealthCardFactory<ECard> eCardFactory, HealthCardFactory<PaperCard> paperCardFactory) {
        if (instance == null) {
            instance = new HealthCardRepository(eCardFactory, paperCardFactory);
        }
        return instance;
    }

    @Override
    public void add(HealthCard newObject) {

        String sql = "INSERT INTO healthcards(expirationDate, PIN, ID, type) VALUES(?, ?, ?, ?);";

        try {
            PreparedStatement stmt = this.connection().prepareStatement(sql);
            stmt.setString(1, newObject.getExpirationDate());
            stmt.setInt(2, newObject.getPin());
            if(newObject instanceof ECard) {
                stmt.setInt(3, ((ECard) newObject).getElectronicID());
                stmt.setString(4,"Electronic");
            }
            else if(newObject instanceof PaperCard){
                stmt.setInt(3, ((PaperCard) newObject).getWrittenID());
                stmt.setString(4, "Paper");
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(HealthCard deletedObject) {
        // Deletion from the database
        String sql = "DELETE FROM healthcards WHERE ID = ?;";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            if(deletedObject instanceof ECard){
                stmt.setInt(1, ((ECard) deletedObject).getElectronicID());
            }
            else if(deletedObject instanceof PaperCard){
                stmt.setInt(1, ((PaperCard) deletedObject).getWrittenID());
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(HealthCard oldObject, HealthCard newObject){
        String sql = "UPDATE healthcards SET expirationDate=?, PIN=?, type=? WHERE ID = ?;";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setString(1, newObject.getExpirationDate());
            stmt.setInt(2, newObject.getPin());

            if (newObject instanceof ECard) {
                stmt.setString(3, "Electronic");
                stmt.setInt(4, ((ECard) newObject).getElectronicID());
            } else if (newObject instanceof PaperCard) {
                stmt.setString(3, "Paper");
                stmt.setInt(4, ((PaperCard) newObject).getWrittenID());
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public ArrayList<HealthCard> readAll() {
        // Reading from the database
        String sql = "SELECT * FROM healthcards;";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            ArrayList<HealthCard> healthCards = new ArrayList<>();

            while (resultSet.next()) {
                int cardID = resultSet.getInt("ID");
                String expirationDate = resultSet.getString("expirationDate");
                int pin = resultSet.getInt("PIN");
                String type = resultSet.getString("type");

                if (type.equals("Electronic")) {
                    int electronicID = resultSet.getInt("ID");
                    ECard eCard = new ECard(expirationDate, pin, electronicID);
                    healthCards.add(eCard);
                } else if (type.equals("Paper")) {
                    int writtenID = resultSet.getInt("ID");
                    PaperCard paperCard = new PaperCard(expirationDate, pin, writtenID);
                    healthCards.add(paperCard);
                }
            }

            return healthCards;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public HealthCard findByIdentifier(ArrayList<String> identifier) {
        // Searching in the database
        String sql = "SELECT * FROM healthcards WHERE ID = ?;";

        try {
            PreparedStatement stmt = connection().prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(identifier.get(0)));
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int cardID = resultSet.getInt("ID");
                String expirationDate = resultSet.getString("expirationDate");
                int pin = resultSet.getInt("PIN");
                String type = resultSet.getString("type");

                if (type.equals("Electronic")) {
                    return new ECard(expirationDate, pin, cardID);
                } else if (type.equals("Paper")) {
                    return new PaperCard(expirationDate, pin, cardID);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ECard createECard(ArrayList<String> eCardData) {
        return eCardFactory.create(eCardData);
    }

    public PaperCard createPaperCard(ArrayList<String> paperCardData) {
        return paperCardFactory.create(paperCardData);
    }
}
