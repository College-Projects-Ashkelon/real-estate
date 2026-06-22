public class RealEstateSystem {
    private User[] users;
    private Property[] properties;
    private Address[] addresses;
    private int userCount;
    private int propertyCount;
    private int addressCount;

    public RealEstateSystem() {
        this.users = new User[100];
        this.properties = new Property[100];
        this.addresses = new Address[10];
        this.userCount = 0;
        this.propertyCount = 0;
        this.addressCount = 0;
        initializeAddresses();
    }

    private void initializeAddresses() {
        addresses[0] = new Address("Tel Aviv", "Rothschild");
        addresses[1] = new Address("Tel Aviv", "Dizengoff");
        addresses[2] = new Address("Tel Aviv", "Ibn Gabirol");
        addresses[3] = new Address("Haifa", "Herzlia");
        addresses[4] = new Address("Haifa", "Hanassi");
        addresses[5] = new Address("Haifa", "Allenby");
        addresses[6] = new Address("Jerusalem", "Jaffa");
        addresses[7] = new Address("Jerusalem", "King George");
        addresses[8] = new Address("Jerusalem", "Agripas");
        addresses[9] = new Address("Jerusalem", "Gaza");
        addressCount = 10;
    }

    public User[] getUsers() {
        return users;
    }

    public Property[] getProperties() {
        return properties;
    }

    public Address[] getAddresses() {
        return addresses;
    }

    public int getUserCount() {
        return userCount;
    }

    public int getPropertyCount() {
        return propertyCount;
    }

    public int getAddressCount() {
        return addressCount;
    }
}

public boolean isStrongPassword(String password) {
    if (password.length() < 5) return false;
    boolean hasDigit = false;
    boolean hasLetter = false;
    for (int i = 0; i < password.length(); i++) {
        char ch = password.charAt(i);
        if (Character.isDigit(ch)) hasDigit = true;
        if (Character.isLetter(ch)) hasLetter = true;
    }
    return hasDigit && hasLetter;
}

public boolean isValidPhone(String phone) {
    if (phone.length() != 10) return false;
    if (!phone.startsWith("05")) return false;
    for (int i = 0; i < phone.length(); i++) {
        if (!Character.isDigit(phone.charAt(i))) return false;
    }
    return true;
}
public boolean addUser(User user) {
    if (userCount >= users.length) return false;
    users[userCount++] = user;
    return true;
}

public boolean addProperty(Property property) {
    if (propertyCount >= properties.length) return false;
    properties[propertyCount++] = property;
    return true;
}
public boolean isUsernameTaken(String username) {
    for (int i = 0; i < userCount; i++) {
        if (users[i].getUsername().equals(username)) return true;
    }
    return false;
}