import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RealEstateSystem system = new RealEstateSystem();
        Scanner scanner = new Scanner(scanner.getProperties());

        while (true) {
            System.out.println("1. Create User");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();

            if (choice == 3) {
                break;
            }
        }
    }
}