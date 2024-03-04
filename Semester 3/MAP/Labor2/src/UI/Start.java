package UI;

import Controller.*;
import Domain.*;
import Factory.ECardFactory;
import Factory.PaperCardFactory;
import Repository.*;

import java.util.ArrayList;
import java.util.Scanner;


public class Start {

    public static DoctorRepository doctorRepository = new DoctorRepository();
    public static PatientRepository patientRepository = new PatientRepository();
    public static MedicationRepository medicationRepository = new MedicationRepository();
    public static SurgeryRepository surgeryRepository = new SurgeryRepository();
    public static SpecializationRepository specializationRepository = new SpecializationRepository();
    public static DiseaseRepository diseaseRepository = new DiseaseRepository();
    public static ConsultationRepository consultationRepository = new ConsultationRepository();
    public static CabinetRepository cabinetRepository = new CabinetRepository();
    public static HospitalRepository hospitalRepository = new HospitalRepository();
    public static DoctorController doctorController = new DoctorController(doctorRepository);
    public static PatientController patientController = new PatientController(patientRepository);
    public static MedicationController medicationController = new MedicationController(medicationRepository);
    public static SurgeryController surgeryController = new SurgeryController(surgeryRepository);
    public static SpecializationController specializationController = new SpecializationController(specializationRepository);
    public static DiseaseController diseaseController = new DiseaseController(diseaseRepository);
    public static ConsultationController consultationController = new ConsultationController(consultationRepository);
    public static CabinetController cabinetController = new CabinetController(cabinetRepository);
    public static HospitalController hospitalController = new HospitalController(hospitalRepository);
    public static ECardFactory eCardFactory = new ECardFactory();
    public static PaperCardFactory paperCardFactory = new PaperCardFactory();
    public static HealthCardRepository healthCardRepository = HealthCardRepository.getInstance(eCardFactory, paperCardFactory);
    public static HealthCardController healthCardController = HealthCardController.getInstance(healthCardRepository);
    public static void run() {
        populate();
        Scanner scanner = new Scanner(System.in);
        String answer = "Yes";
        while (answer.equals("Yes")) {
            DisplayMenu2();
            switch (Integer.parseInt(scanner.nextLine())) {
                case 1:
                    Menu5();
                    break;
                case 2:
                    System.out.println("Which operation do you wish to execute? ");
                    DisplayMenu3();
                    switch (Integer.parseInt(scanner.nextLine())) {
                        case 1:
                            System.out.println("Choose the entity you want to work with.");
                            Menu1();
                            break;
                        case 2:
                            System.out.println("Choose the entity you want to work with.");
                            Menu2();
                            break;
                        case 3:
                            System.out.println("Choose the entity you want to work with.");
                            Menu3();
                            break;
                        case 4:
                            System.out.println("Choose the entity you want to work with.");
                            Menu4();
                            break;
                        case 5:
                            patientController.iteratePatients();
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
            answer = "-";
            while (!answer.equals("No") && !answer.equals("Yes")) {
                System.out.println("Want to continue? (Yes/ No)");
                answer = scanner.nextLine();
                if (answer.equals("No")) {
                    System.out.println("Bye.");
                } else if (!answer.equals("Yes")) {
                    System.out.println("Answer not valid. Try again.");
                }
            }
        }
    }
    public static void Menu1(){{
        DisplayMenu1();
        Scanner scanner = new Scanner(System.in);
        switch (Integer.parseInt(scanner.nextLine())) {
            case 1:
                doctorController.add(ReadFromUserDoctor.readNewObjectData());
                break;
            case 2:
                patientController.add(ReadFromUserPatient.readNewObjectData());
                break;
            case 3:
                medicationController.add(ReadFromUserMedicine.readNewObjectData());
                break;
            case 4:
                surgeryController.add(ReadFromUserSurgery.readNewObjectData());
                break;
            case 5:
                specializationController.add(ReadFromUserDisease.readNewObjectData());
                break;
            case 6:
                diseaseController.add(ReadFromUserDisease.readNewObjectData());
                break;
            case 7:
                cabinetController.add(ReadFromUserCabinet.readNewObjectData());
                break;
            case 8:
                consultationController.add(ReadFromUserConsultation.readNewObjectData());
                break;
            case 9:
                hospitalController.add(ReadFromUserHospital.readNewObjectData());
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }
    }

    public static void Menu2(){
        DisplayMenu1();
        Scanner scanner = new Scanner(System.in);
        switch (Integer.parseInt(scanner.nextLine())) {
            case 1:
                doctorController.update(ReadFromUserDoctor.readIdentifier(), ReadFromUserDoctor.readNewObjectData());
                break;
            case 2:
                patientController.update(ReadFromUserPatient.readIdentifier(), ReadFromUserPatient.readNewObjectData());
                break;
            case 3:
                medicationController.update(ReadFromUserMedicine.readIdentifier(), ReadFromUserMedicine.readNewObjectData());
                break;
            case 4:
                surgeryController.update(ReadFromUserSurgery.readIdentifier(), ReadFromUserSurgery.readNewObjectData());
                break;
            case 5:
                specializationController.update(ReadFromUserSpecialization.readIdentifier(), ReadFromUserDisease.readNewObjectData());
                break;
            case 6:
                diseaseController.update(ReadFromUserDisease.readIdentifier(), ReadFromUserDisease.readNewObjectData());
                break;
            case 7:
                cabinetController.update(ReadFromUserCabinet.readIdentifier(), ReadFromUserCabinet.readNewObjectData());
                break;
            case 8:
                consultationController.update(ReadFromUserConsultation.readIdentifier(), ReadFromUserConsultation.readNewObjectData());
                break;
            case 9:
                hospitalController.update(ReadFromUserHospital.readIdentifier(), ReadFromUserHospital.readNewObjectData());
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    public static void Menu3(){
        DisplayMenu1();
        Scanner scanner = new Scanner(System.in);
        switch (Integer.parseInt(scanner.nextLine())) {
            case 1:
                doctorController.delete(ReadFromUserDoctor.readIdentifier());
                break;
            case 2:
                patientController.delete(ReadFromUserPatient.readIdentifier());
                break;
            case 3:
                medicationController.delete(ReadFromUserMedicine.readIdentifier());
                break;
            case 4:
                surgeryController.delete(ReadFromUserSurgery.readIdentifier());
                break;
            case 5:
                specializationController.delete(ReadFromUserSpecialization.readIdentifier());
                break;
            case 6:
                diseaseController.delete(ReadFromUserDisease.readIdentifier());
                break;
            case 7:
                cabinetController.delete(ReadFromUserCabinet.readIdentifier());
                break;
            case 8:
                consultationController.delete(ReadFromUserConsultation.readIdentifier());
                break;
            case 9:
                hospitalController.delete(ReadFromUserHospital.readIdentifier());
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    public static void Menu4(){
        DisplayMenu1();
        Scanner scanner = new Scanner(System.in);
        switch (Integer.parseInt(scanner.nextLine())) {
            case 1:
                for(Doctor doctor : doctorController.readAll()) {
                    System.out.println(doctor+"\n");
                }
                break;
            case 2:
                for(Patient patient : patientController.readAll()) {
                    System.out.println(patient+"\n");
                }
                break;
            case 3:
                for(Medication medication: medicationController.readAll()) {
                    System.out.println(medication+"\n");
                }
                break;
            case 4:
                for(Surgery surgery : surgeryController.readAll()) {
                    System.out.println(surgery+"\n");
                }
                break;
            case 5:
                for(Specialization specialization : specializationController.readAll()) {
                    System.out.println(specialization+"\n");
                }
                break;
            case 6:
                for(Disease disease : diseaseController.readAll()) {
                    System.out.println(disease+"\n");
                }
                break;
            case 7:
                for(Cabinet cabinet: cabinetController.readAll()) {
                    System.out.println(cabinet+"\n");
                }
                break;
            case 8:
                for(Consultation consultation : consultationController.readAll()) {
                    System.out.println(consultation+"\n");
                }
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }
    public static void Menu5(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("First we want to get to know you. Which is your patientID?");
        ArrayList<String> identifier = new ArrayList<>();
        identifier.add(scanner.nextLine());
        if(patientRepository.findByIdentifier(identifier) == null)
            System.out.println("Sorry, no such patient was found.");
        else{
            boolean runMenu = true;

            while (runMenu) {
                DisplayMenuCard();
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        healthCardController.addECard(ReadFromUserHealthCard.readECardData());
                        break;
                    case 2:
                        healthCardController.addPaperCard(ReadFromUserHealthCard.readPaperCardData());
                        break;
                    case 3:
                        healthCardController.delete(ReadFromUserHealthCard.readIdentifier());
                        break;
                    case 4:
                        healthCardController.update(ReadFromUserHealthCard.readIdentifier(),ReadFromUserHealthCard.readECardData());
                        break;
                    case 5:
                        for(HealthCard healthCard: healthCardController.readAll()) {
                            System.out.println(healthCard+"\n");
                        }
                        break;
                    case 6:
                        runMenu = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    public static void DisplayMenu1() {
        System.out.println("1. Doctor.");
        System.out.println("2. Patient.");
        System.out.println("3. Medication.");
        System.out.println("4. Operation.");
        System.out.println("5. Specialization.");
        System.out.println("6. Disease.");
        System.out.println("7. Cabinet.");
        System.out.println("8. Consultation.");
        System.out.println("9. Hospital.");
    }

    public static void DisplayMenu2() {
        System.out.println("Who uses the database?");
        System.out.println("1. Patient");
        System.out.println("2. Hospital staff");
        System.out.println("Enter your choice: ");
    }

    public static void DisplayMenu3() {
        System.out.println("1. Add.");
        System.out.println("2. Update.");
        System.out.println("3. Delete.");
        System.out.println("4. See all.");
        System.out.println("5. All patients.");
        System.out.println("What do you pick?");
    }

    public static void DisplayMenuCard(){
        System.out.println("1. Add Electronic Card");
        System.out.println("2. Add Paper Card");
        System.out.println("3. Delete Health Card");
        System.out.println("4. Update Card");
        System.out.println("5. Display All Health Cards");
    }


}