public class User {
    private String username;
    private String password;
    private String phone;
    private boolean isBroker;

    public User(String username, String password, String phone, boolean isBroker) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.isBroker = isBroker;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isBroker() {
        return isBroker;
    }

    public boolean checkPassword(String pass) {
        return this.password.equals(pass);
    }

    @Override
    public String toString() {
        return "Username: " + username + ", Phone: " + phone + ", Type: " + (isBroker ? "Broker" : "Regular user");
    }
}