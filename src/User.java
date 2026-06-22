import java.util.Scanner;

public class User {
    public static boolean isDigit(char c){
        if (Character.isDigit(c)) {
            return true;
        }
            return false;
    }
    public static boolean isSpecialChar(char c){
        return !Character.isLetterOrDigit(c) && !Character.isWhitespace(c);

    }
    public static boolean isStrongPassword(String pass){
        boolean hasDigit = false;
        boolean hasSpecialChar = false;
        for (int i = 0; i < pass.length(); i++) {
            char c = pass.charAt(i);

            // בדיקה האם התו הוא ספרה
           if (isDigit(c))
               hasDigit = true;

            // בדיקה האם התו הוא תו מיוחד (לא אות, לא ספרה ולא רווח)
            if (isSpecialChar(c)) {
                hasSpecialChar = true;
            }
        }
        return hasDigit && hasSpecialChar;
    }

    /**
     * בודק האם מספר טלפון הוא מספר ישראלי תקין (10 ספרות, מתחיל ב-05).
     * @param phone מספר הטלפון לבדיקה.
     * @return true אם המספר תקין, אחרת false.
     */
    private static boolean isValidPhone(String phone) {
        // 1. בדיקה שהקלט לא ריק ושהוא באורך 10 תווים
        if (phone == null || phone.length() != 10) {
            return false;
        }

        // 2. בדיקה שהמספר מתחיל ב-"05"
        if (!phone.startsWith("05")) {
            return false;
        }

        // 3. בדיקה שכל התווים הם ספרות
        for (char c : phone.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false; // אם נמצא תו שאינו ספרה, המספר לא תקין
            }
        }

        // אם כל הבדיקות עברו, המספר תקין
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /*
        switch case להשלמה לאחר בניית התשתית עבור תפריט התחברות הרשמה או יציאה
         */
        int selectUser = scanner.nextInt();
        switch (selectUser){
            case 1:
                //System.createuser();
            case 2:
                //login;
            case 3:
                //exit;

        }

        System.out.print("Please enter a password to verify: ");
        String pass = scanner.nextLine();
        boolean isStrong = isStrongPassword(pass);
        System.out.println("Is password strong? " + isStrong);

        System.out.print("\nPlease enter a phone number to verify: ");
        String phone = scanner.nextLine();
        boolean isValid = isValidPhone(phone);
        System.out.println("Is phone number valid? " + isValid);

        scanner.close();
    }
}