//package map.project.demo.UI;
//
//import map.project.demo.Domain.Consultation;
//
//import java.util.ArrayList;
//import java.util.Locale;
//import java.util.Scanner;
//
//public class ReadFromUserConsultation implements UI.ReadFromUserInterface {
//    public static Consultation readNewObjectData() {
//        Scanner scanner = new Scanner(System.in);
//        Consultation newConsutation = new Consultation();
//        System.out.print("ID of the patient (FORMAT DDDD WHERE D- DIGIT): ");
//        newConsutation.setPatientID(Integer.parseInt(scanner.nextLine()));
//        System.out.print("ID of the doctor (FORMAT DDDD WHERE D- DIGIT): ");
//        newConsutation.setDoctorID(Integer.parseInt(scanner.nextLine()));
//        System.out.println("Date of the consultation (FORMAT YYYY-MM-DD WHERE Y- YEAR, M- MONTH, D- DAY): ");
//        newConsutation.setDate(scanner.nextLine());
//        System.out.println("ID of the disease (FORMAT DDDD WHERE D- DIGIT OR NULL): ");
//        newConsutation.setDiseaseID(Integer.parseInt(scanner.nextLine()));
//        System.out.println("HealthCard ownership by the patient (FORMAT true/false): ");
//        if(scanner.nextLine().equalsIgnoreCase("TRUE"))
//            newConsutation.setCard(true);
//        else
//            newConsutation.setCard(false);
//        System.out.println("Price of the consult: ");
//        newConsutation.setPrice(Integer.parseInt(scanner.nextLine()));
//        return newConsutation;
//    }
//    public static int readIdentifier() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("ID of the consultation (FORMAT DDDD WHERE D- DIGIT): ");
//        return Integer.parseInt(scanner.nextLine());
//    }
//
//}
