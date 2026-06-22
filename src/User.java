import java.util.Scanner;

public class User {
    public static boolean isStrongPassword(String pass){
        boolean hasDigit = false;
        boolean hasSpecialChar = false;
        for (int i = 0; i < pass.length(); i++) {
            char c = pass.charAt(i);

            // בדיקה האם התו הוא ספרה
            if (Character.isDigit(c)) {
                hasDigit = true;
            }

            // בדיקה האם התו הוא תו מיוחד (לא אות, לא ספרה ולא רווח)
            if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c)) {
                hasSpecialChar = true;
            }
        }
        return hasDigit && hasSpecialChar;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter a password to verify: ");
        String pass = scanner.nextLine();
        boolean isStrong = isStrongPassword(pass);
        System.out.println(isStrong);

        scanner.close();
    }
}
