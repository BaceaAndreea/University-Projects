//package map.project.demo.UI;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class ReadFromUserHealthCard implements UI.ReadFromUserInterface {
//    private static final Scanner scanner = new Scanner(System.in);
//
//    public static ArrayList<String> readECardData() {
//        ArrayList<String> cardData = new ArrayList<>();
//        System.out.print("Expiration Date: ");
//        cardData.add(scanner.nextLine());
//        System.out.print("PIN: ");
//        cardData.add(scanner.nextLine());
//        System.out.print("Electronic ID: ");
//        cardData.add(scanner.nextLine());
//        return cardData;
//    }
//
//    public static ArrayList<String> readPaperCardData() {
//        ArrayList<String> cardData = new ArrayList<>();
//        System.out.print("Expiration Date: ");
//        cardData.add(scanner.nextLine());
//        System.out.print("PIN: ");
//        cardData.add(scanner.nextLine());
//        System.out.print("Written ID: ");
//        cardData.add(scanner.nextLine());
//        return cardData;
//    }
//
//    public static ArrayList<String> readIdentifier(){
//        ArrayList<String> cardData = new ArrayList<>();
//        System.out.print("ID: ");
//        cardData.add(scanner.nextLine());
//        return cardData;
//    }
//}
