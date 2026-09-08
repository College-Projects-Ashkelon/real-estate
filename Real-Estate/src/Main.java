import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RealEstateSystem system = new RealEstateSystem();
        system.setScanner(scanner);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Real Estate System ===");
            System.out.println("1. Create account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    system.createUser();
                    break;
                case "2":
                    User user = system.login();
                    if (user == null) {
                        System.out.println("Invalid username or password.");
                    } else {
                        userMenu(scanner, system, user);
                    }
                    break;
                case "3":
                    System.out.println("Exiting system. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please choose 1, 2, or 3.");
            }
        }
    }

    public static void userMenu(Scanner scanner, RealEstateSystem system, User user) {
        boolean inUserMenu = true;
        while (inUserMenu) {
            System.out.println("\n--- User Menu (" + user.getUsername() + ") ---");
            System.out.println("1. Publish a new property");
            System.out.println("2. Remove a property");
            System.out.println("3. Display all properties");
            System.out.println("4. My properties");
            System.out.println("5. Search properties");
            System.out.println("6. Logout");
            System.out.print("Choose: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    system.publishProperty(user);
                    break;
                case "2":
                    system.removeProperty(user);
                    break;
                case "3":
                    system.displayAllProperties();
                    break;
                case "4":
                    system.displayUserProperties(user);
                    break;
                case "5":
                    system.searchProperties();
                    break;
                case "6":
                    System.out.println("Logged out successfully.");
                    inUserMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please choose 1-6.");
            }
        }
    }
}