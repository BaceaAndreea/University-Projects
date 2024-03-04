package UI;

import java.util.ArrayList;
import java.util.Scanner;

public class ReadFromUserPatient implements ReadFromUserInterface {
    public static ArrayList<String> readNewObjectData() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> newObjectData = new ArrayList<String>();
        System.out.print("ID of the patient (FORMAT 3DDD WHERE D- DIGIT): ");
        newObjectData.add(scanner.nextLine());
        System.out.print("Last name of the patient: ");
        newObjectData.add(scanner.nextLine());
        System.out.print("First name of the patient: ");
        newObjectData.add(scanner.nextLine());
        System.out.print("Birthday of the patient (FORMAT YYYY-MM-DD WHERE Y- YEAR, M- MONTH, D- DAY): ");
        newObjectData.add(scanner.nextLine());
        System.out.print("Phone number of the patient (FORMAT 555-DDDD-DDDD WHERE D- DIGIT): ");
        newObjectData.add(scanner.nextLine());
        System.out.print("ID of the healthCard of the patient (FORMAT 4DDD WHERE D- DIGIT OR NULL): ");
        newObjectData.add(scanner.nextLine());
        return newObjectData;
    }
    public static ArrayList<String> readIdentifier() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> newObjectData = new ArrayList<String>();
        System.out.print("ID of the Patient (FORMAT 3DDD WHERE D- DIGIT): ");
        newObjectData.add(scanner.nextLine());
        return newObjectData;
    }

}
