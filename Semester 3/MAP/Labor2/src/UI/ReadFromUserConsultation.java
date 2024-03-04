package UI;

import Controller.ControllerInterface;

import java.util.ArrayList;
import java.util.Scanner;

public class ReadFromUserConsultation implements ReadFromUserInterface {
    public static ArrayList<String> readNewObjectData() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> newObjectData = new ArrayList<String>();
        System.out.print("ID of the patient (FORMAT DDDD WHERE D- DIGIT): ");
        newObjectData.add(scanner.nextLine());
        System.out.print("ID of the doctor (FORMAT DDDD WHERE D- DIGIT): ");
        newObjectData.add(scanner.nextLine());
        System.out.println("Date of the consultation (FORMAT YYYY-MM-DD WHERE Y- YEAR, M- MONTH, D- DAY): ");
        newObjectData.add(scanner.nextLine());
        System.out.println("ID of the disease (FORMAT DDDD WHERE D- DIGIT OR NULL): ");
        newObjectData.add(scanner.nextLine());
        System.out.println("HealthCard ownership by the patient (FORMAT true/false): ");
        newObjectData.add(scanner.nextLine());
        System.out.println("Price of the consult: ");
        newObjectData.add(scanner.nextLine());



        return newObjectData;
    }
    public static ArrayList<String> readIdentifier() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> newObjectData = new ArrayList<String>();
        System.out.print("ID of the Patient: ");
        newObjectData.add(scanner.nextLine());
        System.out.print("ID of the Doctor: ");
        newObjectData.add(scanner.nextLine());
        System.out.println("Date: ");
        return newObjectData;
    }

}
