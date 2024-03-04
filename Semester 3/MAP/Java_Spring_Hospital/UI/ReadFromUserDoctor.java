//package map.project.demo.UI;
//
//import map.project.demo.Domain.Doctor;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class ReadFromUserDoctor implements UI.ReadFromUserInterface {
//    public static Doctor readNewObjectData() {
//        Scanner scanner = new Scanner(System.in);
//        Doctor newDoctor = new Doctor();
//        System.out.print("ID of the doctor (FORMAT DDDD WHERE D- DIGIT): ");
//        newDoctor.setDoctorID(Integer.parseInt(scanner.nextLine()));
//        System.out.print("Last name of the doctor: ");
//        newDoctor.setName(scanner.nextLine());
//        System.out.print("First name of the doctor: ");
//        newDoctor.setFirstName(scanner.nextLine());
//        System.out.print("Birthday of the doctor (FORMAT YYYY-MM-DD WHERE Y- YEAR, M- MONTH, D- DAY): ");
//        newDoctor.setBirthdate(scanner.nextLine());
//        System.out.print("ID of the hospital (FORMAT DDDD WHERE D- DIGIT): ");
//        newDoctor.setHospitalID(Integer.parseInt(scanner.nextLine()));
//        System.out.print("Phone number of the doctor (FORMAT 555-DDDD-DDDD WHERE D- DIGIT): ");
//        newDoctor.setContactPhone(scanner.nextLine());
//        System.out.print("ID of the specialization of the Doctor (FORMAT 2DDD WHERE D- DIGIT): ");
//        newDoctor.setSpecializationID(Integer.parseInt(scanner.nextLine()));
//        System.out.print("ID of the cabinet of the Doctor (FORMAT 9DDD WHERE D- DIGIT): ");
//        newDoctor.setCabinetID(Integer.parseInt(scanner.nextLine()));
//        return newDoctor;
//    }
//    public static int readIdentifier() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("ID of the Doctor (FORMAT DDDD WHERE D- DIGIT): ");
//        return Integer.parseInt(scanner.nextLine());
//    }
//
//}
