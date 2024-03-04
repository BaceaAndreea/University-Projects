package UI;

import java.util.ArrayList;
import java.util.Scanner;

public class ReadFromUserMedicine implements ReadFromUserInterface {
    public static ArrayList<String> readNewObjectData() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> newObjectData = new ArrayList<String>();
        System.out.print("ID of the medicine (FORMAT gDDD WHERE D- DIGIT): ");
        newObjectData.add(scanner.nextLine());
        System.out.print("Name of the medicine: ");
        newObjectData.add(scanner.nextLine());
        System.out.print("Way of administration: ");
        newObjectData.add(scanner.nextLine());
        System.out.print("Quantity in storage: ");
        newObjectData.add(scanner.nextLine());
        System.out.print("Expiring date (FORMAT YYYY-MM-DD WHERE Y- YEAR, M- MONTH, D- DAY): ");
        newObjectData.add(scanner.nextLine());
        return newObjectData;
    }
    public static ArrayList<String> readIdentifier() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> newObjectData = new ArrayList<String>();
        System.out.print("ID of the medicine (FORMAT 6DDD WHERE D- DIGIT): ");
        newObjectData.add(scanner.nextLine());
        return newObjectData;
    }

}
