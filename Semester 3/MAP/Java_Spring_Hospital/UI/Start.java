//package map.project.demo.UI;
//
//import map.project.demo.Controller.*;
//import map.project.demo.Domain.*;
//import map.project.demo.Factory.ECardFactory;
//import map.project.demo.Factory.HealthCardFactory;
//import map.project.demo.Factory.PaperCardFactory;
//import map.project.demo.Repository.*;
//import map.project.demo.Service.DoctorService;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//
//public class Start {
//    public static DoctorRepository doctorRepository ;
//    public static DoctorService doctorService = new DoctorService(doctorRepository);
//    public static DoctorController doctorController = new DoctorController(doctorService);
//    public static PatientController patientController = new PatientController();
//    public static MedicationController medicationController = new MedicationController();
//    public static SurgeryController surgeryController = new SurgeryController();
//    public static SpecializationController specializationController = new SpecializationController();
//    public static DiseaseController diseaseController = new DiseaseController();
//    public static ConsultationController consultationController = new ConsultationController();
//    public static CabinetController cabinetController = new CabinetController();
//    public static HospitalController hospitalController = new HospitalController();
//    public static HealthCardFactory<ECard> eCardFactory = new ECardFactory();
//    public static PaperCardFactory paperCardFactory = new PaperCardFactory();
//    //public static HealthCardRepository healthCardRepository = HealthCardRepository.getInstance(eCardFactory, paperCardFactory);
//    //public static HealthCardController healthCardController = HealthCardController.getInstance(healthCardRepository);
//    public static void run() {
//        Scanner scanner = new Scanner(System.in);
//        String answer = "Yes";
//        while (answer.equals("Yes")) {
//            DisplayMenu2();
//            switch (Integer.parseInt(scanner.nextLine())) {
//                case 1:
//                    Menu5();
//                    break;
//                case 2:
//                    System.out.println("Which operation do you wish to execute? ");
//                    DisplayMenu3();
//                    switch (Integer.parseInt(scanner.nextLine())) {
//                        case 1:
//                            System.out.println("Choose the entity you want to work with.");
//                            Menu1();
//                            break;
//                        case 2:
//                            System.out.println("Choose the entity you want to work with.");
//                            Menu2();
//                            break;
//                        case 3:
//                            System.out.println("Choose the entity you want to work with.");
//                            Menu3();
//                            break;
//                        case 4:
//                            System.out.println("Choose the entity you want to work with.");
//                            Menu4();
//                            break;
//                        case 5:
//                            patientController.iteratePatients();
//                            break;
//                        default:
//                            System.out.println("Invalid choice.");
//                            break;
//                    }
//                    break;
//                default:
//                    System.out.println("Invalid choice.");
//                    break;
//            }
//            answer = "-";
//            while (!answer.equals("No") && !answer.equals("Yes")) {
//                System.out.println("Want to continue? (Yes/ No)");
//                answer = scanner.nextLine();
//                if (answer.equals("No")) {
//                    System.out.println("Bye.");
//                } else if (!answer.equals("Yes")) {
//                    System.out.println("Answer not valid. Try again.");
//                }
//            }
//        }
//    }
//    public static void Menu1(){{
//        DisplayMenu1();
//        Scanner scanner = new Scanner(System.in);
//        switch (Integer.parseInt(scanner.nextLine())) {
//            case 1:
//                doctorController.add(ReadFromUserDoctor.readNewObjectData());
//                break;
//            case 2:
//                patientController.add(ReadFromUserPatient.readNewObjectData());
//                break;
//            case 3:
//                medicationController.add(ReadFromUserMedicine.readNewObjectData());
//                break;
//            case 4:
//                surgeryController.add(ReadFromUserSurgery.readNewObjectData());
//                break;
//            case 5:
//                specializationController.add(ReadFromUserSpecialization.readNewObjectData());
//                break;
//            case 6:
//                diseaseController.add(ReadFromUserDisease.readNewObjectData());
//                break;
//            case 7:
//                cabinetController.add(ReadFromUserCabinet.readNewObjectData());
//                break;
//            case 8:
//                consultationController.add(ReadFromUserConsultation.readNewObjectData());
//                break;
//            case 9:
//                hospitalController.add(ReadFromUserHospital.readNewObjectData());
//                break;
//            default:
//                System.out.println("Invalid choice.");
//                break;
//        }
//    }
//    }
//
//    public static void Menu2(){
//        DisplayMenu1();
//        Scanner scanner = new Scanner(System.in);
//        switch (Integer.parseInt(scanner.nextLine())) {
//            case 1:
//                doctorController.update(ReadFromUserDoctor.readIdentifier(), ReadFromUserDoctor.readNewObjectData());
//                break;
//            case 2:
//                patientController.update(ReadFromUserPatient.readIdentifier(), ReadFromUserPatient.readNewObjectData());
//                break;
//            case 3:
//                medicationController.update(ReadFromUserMedicine.readIdentifier(), ReadFromUserMedicine.readNewObjectData());
//                break;
//            case 4:
//                surgeryController.update(ReadFromUserSurgery.readIdentifier(), ReadFromUserSurgery.readNewObjectData());
//                break;
//            case 5:
//                specializationController.update(ReadFromUserSpecialization.readIdentifier(), ReadFromUserSpecialization.readNewObjectData());
//                break;
//            case 6:
//                diseaseController.update(ReadFromUserDisease.readIdentifier(), ReadFromUserDisease.readNewObjectData());
//                break;
//            case 7:
//                cabinetController.update(ReadFromUserCabinet.readIdentifier(), ReadFromUserCabinet.readNewObjectData());
//                break;
//            case 8:
//                consultationController.update(ReadFromUserConsultation.readIdentifier(), ReadFromUserConsultation.readNewObjectData());
//                break;
//            case 9:
//                hospitalController.update(ReadFromUserHospital.readIdentifier(), ReadFromUserHospital.readNewObjectData());
//                break;
//            default:
//                System.out.println("Invalid choice.");
//                break;
//        }
//    }
//
//    public static void Menu3(){
//        DisplayMenu1();
//        Scanner scanner = new Scanner(System.in);
//        switch (Integer.parseInt(scanner.nextLine())) {
//            case 1:
//                doctorController.delete(ReadFromUserDoctor.readIdentifier());
//                break;
//            case 2:
//                patientController.delete(ReadFromUserPatient.readIdentifier());
//                break;
//            case 3:
//                medicationController.delete(ReadFromUserMedicine.readIdentifier());
//                break;
//            case 4:
//                surgeryController.delete(ReadFromUserSurgery.readIdentifier());
//                break;
//            case 5:
//                specializationController.delete(ReadFromUserSpecialization.readIdentifier());
//                break;
//            case 6:
//                diseaseController.delete(ReadFromUserDisease.readIdentifier());
//                break;
//            case 7:
//                cabinetController.delete(ReadFromUserCabinet.readIdentifier());
//                break;
//            case 8:
//                consultationController.delete(ReadFromUserConsultation.readIdentifier());
//                break;
//            case 9:
//                hospitalController.delete(ReadFromUserHospital.readIdentifier());
//                break;
//            default:
//                System.out.println("Invalid choice.");
//                break;
//        }
//    }
//
//    public static void Menu4(){
//        DisplayMenu1();
//        Scanner scanner = new Scanner(System.in);
//        switch (Integer.parseInt(scanner.nextLine())) {
//            case 1:
//                doctorController.printAll();
//                break;
//            case 2:
//                patientController.printAll();
//                break;
//            case 3:
//                medicationController.printAll();
//                break;
//            case 4:
//                surgeryController.printAll();
//                break;
//            case 5:
//                specializationController.printAll();
//                break;
//            case 6:
//                diseaseController.printAll();
//                break;
//            case 7:
//                cabinetController.printAll();
//                break;
//            case 8:
//
//                consultationController.printAll();
//                break;
//            default:
//                System.out.println("Invalid choice.");
//                break;
//        }
//    }
//    public static void Menu5(){
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("First we want to get to know you. Which is your patientID?");
//        int identifier6 = Integer.parseInt(scanner.nextLine());
//            boolean runMenu = true;
//
//            while (runMenu) {
//                DisplayMenuCard();
//                int choice = Integer.parseInt(scanner.nextLine());
//                switch (choice) {
//                    case 1:
//                        //healthCardController.addECard(ReadFromUserHealthCard.readECardData());
//                        break;
//                    case 2:
//                        //healthCardController.addPaperCard(ReadFromUserHealthCard.readPaperCardData());
//                        break;
//                    case 3:
//                        //healthCardController.delete(ReadFromUserHealthCard.readIdentifier());
//                        break;
//                    case 4:
//                        //healthCardController.update(ReadFromUserHealthCard.readIdentifier(),ReadFromUserHealthCard.readECardData());
//                        break;
//                    case 5:
//                        //healthcardController.printAll();
//                        break;
//                    case 6:
//                        runMenu = false;
//                        break;
//                    default:
//                        System.out.println("Invalid choice. Please try again.");
//                }
//            }
//        }
//
//    public static void DisplayMenu1() {
//        System.out.println("1. Doctor.");
//        System.out.println("2. Patient.");
//        System.out.println("3. Medication.");
//        System.out.println("4. Operation.");
//        System.out.println("5. Specialization.");
//        System.out.println("6. Disease.");
//        System.out.println("7. Cabinet.");
//        System.out.println("8. Consultation.");
//        System.out.println("9. Hospital.");
//    }
//
//    public static void DisplayMenu2() {
//        System.out.println("Who uses the database?");
//        System.out.println("1. Patient");
//        System.out.println("2. Hospital staff");
//        System.out.println("Enter your choice: ");
//    }
//
//    public static void DisplayMenu3() {
//        System.out.println("1. Add.");
//        System.out.println("2. Update.");
//        System.out.println("3. Delete.");
//        System.out.println("4. See all.");
//        System.out.println("5. All patients.");
//        System.out.println("What do you pick?");
//    }
//
//    public static void DisplayMenuCard(){
//        System.out.println("1. Add Electronic Card");
//        System.out.println("2. Add Paper Card");
//        System.out.println("3. Delete Health Card");
//        System.out.println("4. Update Card");
//        System.out.println("5. Display All Health Cards");
//        System.out.println("6. Exit");
//    }
//
//
//}