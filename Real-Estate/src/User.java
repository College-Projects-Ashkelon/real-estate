public class User {
    private String username;
    private String password;
    private String phone;
    private boolean isBroker;

    public User(String username, String password, String phone, boolean isBroker) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (!isValidPassword(password)) {
            throw new IllegalArgumentException("Invalid password");
        }
        if (!isValidPhone(phone)) {
            throw new IllegalArgumentException("Invalid phone number");
        }
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.isBroker = isBroker;
    }

    public String getUsername() { return username; }
    public String getPhone() { return phone; }
    public boolean isBroker() { return isBroker; }

    public boolean checkPassword(String inputPassword) {
        if (this.password == null || inputPassword == null) {
            return false;
        }
        return this.password.equals(inputPassword);
    }

    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.length() != 10 || !phone.startsWith("05")) {
            return false;
        }
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidPassword(String pass) {
        if (pass == null || pass.length() < 4 || pass.contains(" ")) {
            return false;
        }
        boolean hasDigit = false;
        boolean hasSpecial = false;
        for (char c : pass.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (c == '$' || c == '%' || c == '_') {
                hasSpecial = true;
            }
        }
        return hasDigit && hasSpecial;
    }

    @Override
    public String toString() {
        String role = isBroker ? "real estate broker" : "regular user";
        return username + " " + phone + " (" + role + ")";
    }
}