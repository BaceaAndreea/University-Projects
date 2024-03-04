//package map.project.demo.UI;
//
//import map.project.demo.Domain.Medication;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class ReadFromUserMedicine implements UI.ReadFromUserInterface {
//    public static Medication readNewObjectData() {
//        Scanner scanner = new Scanner(System.in);
//        Medication newMedication = new Medication();
//        System.out.print("ID of the medicine (FORMAT DDDD WHERE D- DIGIT): ");
//        newMedication.setMedicationID(Integer.parseInt(scanner.nextLine()));
//        System.out.print("Name of the medicine: ");
//        newMedication.setName(scanner.nextLine());
//        System.out.print("Way of administration: ");
//        newMedication.setAdministrationRoute(scanner.nextLine());
//        System.out.print("Quantity in storage: ");
//        newMedication.setStorageAmount(Integer.parseInt(scanner.nextLine()));
//        System.out.print("Expiring date (FORMAT YYYY-MM-DD WHERE Y- YEAR, M- MONTH, D- DAY): ");
//        newMedication.setExpirationDate(scanner.nextLine());
//        return newMedication;
//    }
//    public static int readIdentifier() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("ID of the medicine (FORMAT DDDD WHERE D- DIGIT): ");
//        return Integer.parseInt(scanner.nextLine());
//    }
//
//}
