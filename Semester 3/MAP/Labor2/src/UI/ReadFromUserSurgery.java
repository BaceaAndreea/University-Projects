package UI;

import java.util.ArrayList;
import java.util.Scanner;

public class ReadFromUserSurgery implements ReadFromUserInterface {
    public static ArrayList<String> readNewObjectData() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> newObjectData = new ArrayList<String>();
        System.out.print("ID of the patient (FORMAT DDDD WHERE D- DIGIT): ");
        newObjectData.add(scanner.nextLine());
        System.out.print("ID of the doctor (FORMAT DDDD WHERE D- DIGIT): ");
        newObjectData.add(scanner.nextLine());
        System.out.println("Date of the surgery (FORMAT YYYY-MM-DD WHERE Y- YEAR, M- MONTH, D- DAY): ");
        newObjectData.add(scanner.nextLine());
        System.out.println("ID of the disease (FORMAT DDDD WHERE D- DIGIT): ");
        newObjectData.add(scanner.nextLine());
        System.out.println("Name of the surgery: ");
        newObjectData.add(scanner.nextLine());
        System.out.println("ID of the medication (FORMAT DDDD WHERE D- DIGIT OR NULL): ");
        newObjectData.add(scanner.nextLine());
        return newObjectData;
    }
    public static ArrayList<String> readIdentifier() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> newObjectData = new ArrayList<String>();
        System.out.print("ID of the Patient (FORMAT DDDD WHERE D- DIGIT): ");
        newObjectData.add(scanner.nextLine());
        System.out.print("ID of the Doctor (FORMAT DDDD WHERE D- DIGIT): ");
        newObjectData.add(scanner.nextLine());
        System.out.println("Date of the surgery (FORMAT YYYY-MM-DD WHERE Y- YEAR, M- MONTH, D- DAY): ");
        return newObjectData;
    }

}
