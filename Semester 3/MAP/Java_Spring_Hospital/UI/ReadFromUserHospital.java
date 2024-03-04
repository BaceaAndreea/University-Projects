//package map.project.demo.UI;
//
//import map.project.demo.Domain.Hospital;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class ReadFromUserHospital implements UI.ReadFromUserInterface {
//    public static Hospital readNewObjectData() {
//        Scanner scanner = new Scanner(System.in);
//        Hospital newHospital = new Hospital();
//        System.out.print("ID of the hospital (FORMAT DDDD WHERE D- DIGIT): ");
//        newHospital.setHospitalID(Integer.parseInt(scanner.nextLine()));
//        System.out.print("Name of the hospital: ");
//        newHospital.setName(scanner.nextLine());
//        System.out.println("Capacity (NUMBER OF BEDS): ");
//        newHospital.setCapacity(Integer.parseInt(scanner.nextLine()));
//        return newHospital;
//    }
//    public static int readIdentifier() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("ID of the hospital (FORMAT DDDD WHERE D- DIGIT): ");
//        return Integer.parseInt(scanner.nextLine());
//    }
//
//}
