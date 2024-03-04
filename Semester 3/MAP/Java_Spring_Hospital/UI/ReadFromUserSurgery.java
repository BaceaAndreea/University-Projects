//package map.project.demo.UI;
//
//import map.project.demo.Domain.Surgery;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class ReadFromUserSurgery implements UI.ReadFromUserInterface {
//    public static Surgery readNewObjectData() {
//        Scanner scanner = new Scanner(System.in);
//        Surgery newSurgery = new Surgery();
//        System.out.print("ID of the patient (FORMAT DDDD WHERE D- DIGIT): ");
//        newSurgery.setPatientID(Integer.parseInt(scanner.nextLine()));
//        System.out.print("ID of the doctor (FORMAT DDDD WHERE D- DIGIT): ");
//        newSurgery.setDoctorID(Integer.parseInt(scanner.nextLine()));
//        System.out.println("Date of the surgery (FORMAT YYYY-MM-DD WHERE Y- YEAR, M- MONTH, D- DAY): ");
//        newSurgery.setDate(scanner.nextLine());
//        System.out.println("ID of the disease (FORMAT DDDD WHERE D- DIGIT): ");
//        newSurgery.setDiseaseID(Integer.parseInt(scanner.nextLine()));
//        System.out.println("Name of the surgery: ");
//        newSurgery.setName(scanner.nextLine());
//        System.out.println("ID of the medication (FORMAT DDDD WHERE D- DIGIT OR NULL): ");
//        newSurgery.setMedicationID(Integer.parseInt(scanner.nextLine()));
//        return newSurgery;
//    }
//    public static int readIdentifier() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("ID of the surgery (FORMAT DDDD WHERE D- DIGIT): ");
//        return Integer.parseInt(scanner.nextLine());
//    }
//
//}
