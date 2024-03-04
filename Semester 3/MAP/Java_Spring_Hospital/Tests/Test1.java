//package Tests;
//
//import Controller.DoctorController;
//import Controller.DiseaseController;
//import Controller.PatientController;
//import Domain.Doctor;
//import Domain.Disease;
//import Domain.Patient;
//import Repository.DoctorRepository;
//import Repository.DiseaseRepository;
//import Repository.PatientRepository;
//import org.junit.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//
//public class Test1 {
//
//    @Test
//    public void addUpdateDeletePatient() {
//        PatientRepository pr = new PatientRepository();
//        Patient anaPatient = new Patient(1001, "Abrac", "Ana", "19920208", "7778656542", 101);
//        pr.add(anaPatient);
//        ArrayList<Patient> patients = pr.readAll();
//        assertEquals(patients.get(0).toString(), anaPatient.toString());
//        System.out.println("Add Test Patient successful");
//        Patient mariaPatient = new Patient(1001, "Abrac", "Maria", "20000408", "7778656542", 101);
//        pr.update(anaPatient, mariaPatient);
//        patients = pr.readAll();
//        assertEquals(patients.get(0).toString(), mariaPatient.toString());
//        System.out.println("Update Test Patient successful");
//        pr.delete(mariaPatient);
//        patients = pr.readAll();
//        assertTrue(patients.isEmpty());
//        System.out.println("Delete Test Patient successful");
//    }
//
//    @Test
//    public void addUpdateDeleteIllness(){
//        DiseaseRepository dr = new DiseaseRepository();
//        Disease badIllness = new Disease(101, "Bad Illness");
//        dr.add(badIllness);
//        ArrayList<Disease> diseases = dr.readAll();
//        assertEquals(diseases.get(0).toString(), badIllness.toString());
//        System.out.println("Add Test Illness successful");
//        Disease veryBadIllness = new Disease(101, "Very Bad Illness");
//        dr.update(badIllness, veryBadIllness);
//        diseases = dr.readAll();
//        assertEquals(diseases.get(0).toString(), veryBadIllness.toString());
//        System.out.println("Update Test Illness successful");
//        dr.delete(veryBadIllness);
//        diseases = dr.readAll();
//        assertTrue(diseases.isEmpty());
//        System.out.println("Delete Test Illness successful");
//    }
//
//}
