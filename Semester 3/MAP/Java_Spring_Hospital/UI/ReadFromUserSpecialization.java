//package map.project.demo.UI;
//
//import map.project.demo.Domain.Specialization;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class ReadFromUserSpecialization implements UI.ReadFromUserInterface {
//    public static Specialization readNewObjectData() {
//        Scanner scanner = new Scanner(System.in);
//        Specialization newSpecialization = new Specialization();
//        System.out.print("ID of the specialization (FORMAT DDDD WHERE D- DIGIT): ");
//        newSpecialization.setSpecializationID(Integer.parseInt(scanner.nextLine()));
//        System.out.print("Name of the specialization: ");
//        newSpecialization.setName(scanner.nextLine());
//        return newSpecialization;
//    }
//    public static int readIdentifier() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("ID of the specialization (FORMAT DDDD WHERE D- DIGIT): ");
//        return Integer.parseInt(scanner.nextLine());
//    }
//
//}
