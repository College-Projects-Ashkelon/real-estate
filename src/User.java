public class User {

    // --- שדות ---
    private String username;
    private String password;
    private String phone;
    private boolean isBroker; // תיקנתי את טעות הכתיב

    // --- בנאי ---
    public User(String username, String password, String phone, boolean isBroker) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.isBroker = isBroker; // תיקנתי את טעות הכתיב
    }

    // --- Getters ---
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isBroker() {
        return isBroker;
    }

    // --- מתודות מהדרישה ---

    /**
     * בודק אם הסיסמה שהתקבלה כקלט זהה לסיסמה של המשתמש.
     * @param input הסיסמה לבדיקה.
     * @return true אם הסיסמאות זהות, אחרת false.
     */
    public boolean checkPassword(String input) {
        // משתמשים ב-equals להשוואת מחרוזות
        return this.password.equals(input);
    }

    /**
     * מחזיר ייצוג טקסטואלי של המשתמש לפי הפורמט הנדרש.
     */
    @Override
    public String toString() {
        String brokerStatus = this.isBroker ? "yes" : "no";
        return this.username + " | " + this.phone + " | broker: " + brokerStatus;
    }

    // --- מתודות עזר סטטיות (מהקוד שלך) ---

    public static boolean isStrongPassword(String pass) {
        if (pass == null) return false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;
        for (char c : pass.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetter(c)) { // תו מיוחד הוא כל מה שאינו אות או ספרה
                hasSpecialChar = true;
            }
        }
        return hasDigit && hasSpecialChar;
    }

    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.length() != 10) {
            return false;
        }
        if (!phone.startsWith("05")) {
            return false;
        }
        for (char c : phone.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    // --- מתודת main להדגמה ---
    public static void main(String[] args) {
        // יצירת משתמש חדש (מתווך)
        User brokerUser = new User("yossi", "Yossi123$", "0541234567", true);

        // יצירת משתמש חדש (רגיל)
        User regularUser = new User("dani", "Dani456!", "0527654321", false);

        // הדפסת פרטי המשתמשים באמצעות toString()
        System.out.println("--- User Details ---");
        System.out.println(brokerUser); // יקרא אוטומטית ל-brokerUser.toString()
        System.out.println(regularUser);

        // בדיקת סיסמה
        System.out.println("\n--- Password Check ---");
        System.out.println("Checking password for yossi: " + brokerUser.checkPassword("wrongPass")); // false
        System.out.println("Checking password for yossi: " + brokerUser.checkPassword("Yossi123$")); // true

        // בדיקות חוקיות סטטיות
        System.out.println("\n--- Static Validations ---");
        System.out.println("Is '050111222' a valid phone? " + User.isValidPhone("050111222")); // false (9 ספרות)
        System.out.println("Is '0501112223' a valid phone? " + User.isValidPhone("0501112223")); // true
        System.out.println("Is 'pass' a strong password? " + User.isStrongPassword("pass")); // false
        System.out.println("Is 'pass123$' a strong password? " + User.isStrongPassword("pass123$")); // true
    }
}