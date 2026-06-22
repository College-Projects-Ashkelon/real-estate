/**
 * מחלקה המייצגת משתמש או פעולה במערכת הנדל"ן.
 */
public class User {
    public static boolean isStrongPassword(String password){
        boolean hasDigit = false;
        boolean hasSpecialChar = false;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

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
    public static void main(String[] args){
        System.out.println(isStrongPassword("123y!i"));
    }
}