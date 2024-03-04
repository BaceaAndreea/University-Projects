package map.project.demo.Decorator;

public interface Decorator {

    void displayDetails(int id);

     default String addEmptySpace(int length, int totalLength) {
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < totalLength - length; i++) {
            spaces.append(" ");
        }
        return spaces.toString();
    }
}
