package Domain;

public class PaperCard extends HealthCard{

    private int writtenID;

    public PaperCard(String expirationDate, int pin, int writtenID) {
        super(expirationDate, pin);
        this.writtenID = writtenID;
    }

    public int getWrittenID() {
        return writtenID;
    }

    public void setWrittenID(int writtenID) {
        this.writtenID = writtenID;
    }

    @Override
    public String toString() {
        return "PaperCard{" +
                "writtenID=" + writtenID +
                '}';
    }
}
