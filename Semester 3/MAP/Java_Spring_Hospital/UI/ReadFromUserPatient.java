//package map.project.demo.UI;
//
//import map.project.demo.Domain.Patient;
//
//import java.io.IOError;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class ReadFromUserPatient implements UI.ReadFromUserInterface {
//    public static Patient readNewObjectData() {
//        Scanner scanner = new Scanner(System.in);
//        Patient newPatient = new Patient();
//        System.out.print("ID of the patient (FORMAT DDDD WHERE D- DIGIT): ");
//        newPatient.setPatientID(Integer.parseInt(scanner.nextLine()));
//        System.out.print("Last name of the patient: ");
//        newPatient.setName(scanner.nextLine());
//        System.out.print("First name of the patient: ");
//        newPatient.setFirstName(scanner.nextLine());
//        System.out.print("Birthday of the patient (FORMAT YYYY-MM-DD WHERE Y- YEAR, M- MONTH, D- DAY): ");
//        newPatient.setBirthdate(scanner.nextLine());
//        System.out.print("Phone number of the patient (FORMAT 555-DDDD-DDDD WHERE D- DIGIT): ");
//        newPatient.setContactPhone(scanner.nextLine());
//        System.out.print("ID of the healthCard of the patient (FORMAT DDDD WHERE D- DIGIT OR NULL): ");
//        newPatient.setCardID(Integer.parseInt(scanner.nextLine()));
//        return newPatient;
//    }
//    public static int readIdentifier() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("ID of the Patient (FORMAT DDDD WHERE D- DIGIT): ");
//        return Integer.parseInt(scanner.nextLine());
//    }
//
//}
