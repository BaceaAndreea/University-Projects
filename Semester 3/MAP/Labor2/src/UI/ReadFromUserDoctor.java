package UI;

import java.util.ArrayList;
import java.util.Scanner;

public class ReadFromUserDoctor implements ReadFromUserInterface {
    public static ArrayList<String> readNewObjectData() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> newObjectData = new ArrayList<String>();
        System.out.print("ID of the doctor (FORMAT 1DDD WHERE D- DIGIT): ");
        newObjectData.add(scanner.nextLine());
        System.out.print("Last name of the doctor: ");
        newObjectData.add(scanner.nextLine());
        System.out.print("First name of the doctor: ");
        newObjectData.add(scanner.nextLine());
        System.out.print("Birthday of the doctor (FORMAT YYYY-MM-DD WHERE Y- YEAR, M- MONTH, D- DAY): ");
        newObjectData.add(scanner.nextLine());
        System.out.print("ID of the hospital (FORMAT 5DDD WHERE D- DIGIT): ");
        newObjectData.add(scanner.nextLine());
        System.out.print("Phone number of the doctor (FORMAT 555-DDDD-DDDD WHERE D- DIGIT): ");
        newObjectData.add(scanner.nextLine());
        System.out.print("ID of the specialization of the Doctor (FORMAT 2DDD WHERE D- DIGIT): ");
        newObjectData.add(scanner.nextLine());
        System.out.print("ID of the cabinet of the Doctor (FORMAT 9DDD WHERE D- DIGIT): ");
        newObjectData.add(scanner.nextLine());
        return newObjectData;
    }
    public static ArrayList<String> readIdentifier() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> newObjectData = new ArrayList<String>();
        System.out.print("ID of the Doctor (FORMAT 1DDD WHERE D- DIGIT): ");
        newObjectData.add(scanner.nextLine());
        return newObjectData;
    }

}
