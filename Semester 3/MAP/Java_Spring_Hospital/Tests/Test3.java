//package Tests;
//
//import map.project.demo.Repository.MedicationRepository;
//import map.project.demo.Repository.DiseaseRepository;
//import map.project.demo.Domain.Medication;
////import map.project.demo.Domain.Disease;
//import map.project.demo.Service.DiseaseSrvice;
//import org.testng.annotations.Test;
//
//import java.util.ArrayList;
//
//import static org.junit.Assert.assertEquals;
//
//class Test3 {
//    private final DiseaseService diseaseService = new DiseaseService();
//
//    private final MedicationRepository medicationRepository = new MedicationRepository();
//
//    @Test
//    void testMedication() {
//
//        Medication medicationToAdd = new Medication(1, "MedicineX", "Oral", 100, "2024-12-31");
//        medicationRepository.add(medicationToAdd);
//
//        Medication lastMedicationAdded = medicationRepository.readAll().get(0);
//        assertEquals(lastMedicationAdded.getMedicationID(), medicationToAdd.getMedicationID());
//        assertEquals(lastMedicationAdded.getExpirationDate(), medicationToAdd.getExpirationDate());
//        assertEquals(lastMedicationAdded.getStorageAmount(), medicationToAdd.getStorageAmount());
//        assertEquals(lastMedicationAdded.getName(), medicationToAdd.getName());
//        assertEquals(lastMedicationAdded.getAdministrationRoute(), medicationToAdd.getAdministrationRoute());
//        System.out.println("ADD successful");
//
//        Medication modifiedMedication = new Medication(1, "UpdatedMedicineX", "Injection", 150, "2025-12-31");
//        medicationRepository.update(medicationToAdd, modifiedMedication);
//        lastMedicationAdded = medicationRepository.readAll().get(0);
//        assertEquals(lastMedicationAdded.getMedicationID(), modifiedMedication.getMedicationID());
//        assertEquals(lastMedicationAdded.getExpirationDate(), modifiedMedication.getExpirationDate());
//        assertEquals(lastMedicationAdded.getStorageAmount(), modifiedMedication.getStorageAmount());
//        assertEquals(lastMedicationAdded.getName(), modifiedMedication.getName());
//        assertEquals(lastMedicationAdded.getAdministrationRoute(), modifiedMedication.getAdministrationRoute());
//        System.out.println("UPDATE successful");
//
//        medicationRepository.delete(modifiedMedication);
//
//        ArrayList<Medication> medicationsAfterDeletion = medicationRepository.readAll();
//        assertFalse(medicationsAfterDeletion.contains(modifiedMedication));
//        System.out.println("DELETE successful");
//    }
//    @Test
//    void testDisease() {
//        Disease diseaseToAdd = new Disease(1, "Cold");
//        diseaseRepository.add(diseaseToAdd);
//
//        Disease lastDiseaseAdded = diseaseRepository.readAll().get(0);
//        assertEquals(lastDiseaseAdded.getDiseaseID(), diseaseToAdd.getDiseaseID());
//        assertEquals(lastDiseaseAdded.getName(), diseaseToAdd.getName());
//        System.out.println("ADD successful");
//
//        Disease modifiedDisease = new Disease(1, "Flu");
//        diseaseRepository.update(diseaseToAdd, modifiedDisease);
//
//        lastDiseaseAdded = diseaseRepository.readAll().get(0);
//        assertEquals(lastDiseaseAdded.getDiseaseID(), modifiedDisease.getDiseaseID());
//        assertEquals(lastDiseaseAdded.getName(), modifiedDisease.getName());
//        System.out.println("UPDATE successful");
//
//        diseaseRepository.delete(modifiedDisease);
//
//        ArrayList<Disease> diseasesAfterDeletion = diseaseRepository.readAll();
//        assertFalse(diseasesAfterDeletion.contains(modifiedDisease));
//        System.out.println("DELETE successful");
//    }
//}
