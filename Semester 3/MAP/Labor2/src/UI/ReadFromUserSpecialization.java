package UI;

import java.util.ArrayList;
import java.util.Scanner;

public class ReadFromUserSpecialization implements ReadFromUserInterface {
    public static ArrayList<String> readNewObjectData() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> newObjectData = new ArrayList<String>();
        System.out.print("ID of the specialization (FORMAT 2DDD WHERE D- DIGIT): ");
        newObjectData.add(scanner.nextLine());
        System.out.print("Name of the specialization: ");
        newObjectData.add(scanner.nextLine());
        return newObjectData;
    }
    public static ArrayList<String> readIdentifier() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> newObjectData = new ArrayList<String>();
        System.out.print("ID of the specialization (FORMAT 2DDD WHERE D- DIGIT): ");
        newObjectData.add(scanner.nextLine());
        return newObjectData;
    }

}
