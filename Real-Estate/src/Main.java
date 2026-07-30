import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RealEstateSystem system = new RealEstateSystem();
        boolean running = true;

        while (running) {
            System.out.println("=== Real Estate System ===");
            System.out.println("1. Create account");
            System.out.println("2. Login");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // TODO: REQ-001 createUser()
                    break;
                case "2":
                    // TODO: