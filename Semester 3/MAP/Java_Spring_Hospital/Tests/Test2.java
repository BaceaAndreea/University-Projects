//package Tests;
//
//import org.junit.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.ArrayList;
//
//import Domain.*;
//import Repository.*;
//import Controller.*;
//import Factory.*;
//
//
//public class Test2 {
//
//    @Test
//    public void addUpdateDeleteDoctor() {
//        DoctorRepository doctorRepository = new DoctorRepository();
//
//        Doctor johnDoctor = new Doctor.Builder(101, "Smith", "John")
//                .birthdate("19990302")
//                .hospitalID(303)
//                .contactPhone("5553456236")
//                .specializationID(100)
//                .cabinetID(200)
//                .build();
//
//        doctorRepository.add(johnDoctor);
//        ArrayList<Doctor> doctors = doctorRepository.readAll();
//        assertEquals(doctors.get(0).toString(), johnDoctor.toString());
//        System.out.println("Add Test Doctor successful");
//
//        Doctor jonathanDoctor = new Doctor.Builder(101, "Smith", "Jonathan")
//                .birthdate("20000302")
//                .hospitalID(303)
//                .contactPhone("5553456236")
//                .specializationID(100)
//                .cabinetID(200)
//                .build();
//
//        doctorRepository.update(johnDoctor, jonathanDoctor);
//
//        doctors = doctorRepository.readAll();
//        assertEquals(doctors.get(0).toString(), jonathanDoctor.toString());
//        System.out.println("Update Test Doctor successful");
//
//        doctorRepository.delete(jonathanDoctor);
//
//        doctors = doctorRepository.readAll();
//        assertTrue(doctors.isEmpty());
//        System.out.println("Delete Test Doctor successful");
//    }
//
//    @Test
//    public void addUpdateDeleteHealthcard(){
//        ECardFactory eCardFactory = new ECardFactory();
//        PaperCardFactory paperCardFactory = new PaperCardFactory();
//        HealthCardRepository hr = HealthCardRepository.getInstance(eCardFactory, paperCardFactory);
//        HealthCard card1 = new ECard("20000405", 3333, 3098);
//        HealthCard card2 = new PaperCard("20000405", 3233, 4098);
//        hr.add(card1);
//        hr.add(card2);
//        ArrayList<HealthCard> cards = hr.readAll();
//        assertEquals(cards.get(0).toString(), card1.toString());
//        assertEquals(cards.get(1).toString(), card2.toString());
//        System.out.println("Add Test ECard and PaperCard successful");
//        HealthCard card3 = new ECard("19990405", 2233, 4098);
//        HealthCard card4 = new PaperCard("20010405", 3533, 5098);
//        hr.update(card1, card3);
//        hr.update(card2, card4);
//        cards = hr.readAll();
//        assertEquals(cards.get(0).toString(), card3.toString());
//        assertEquals(cards.get(1).toString(), card4.toString());
//        System.out.println("Update Test ECard and PaperCard successful");
//        hr.delete(card3);
//        hr.delete(card4);
//        cards = hr.readAll();
//        assertTrue(cards.isEmpty());
//        System.out.println("Delete Test ECard and PaperCard successful");
//    }
//
//
//}
