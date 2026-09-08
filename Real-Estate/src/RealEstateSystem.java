import java.util.Scanner;

public class RealEstateSystem {
    private User[] users;
    private int userCount;
    private Address[] addresses;
    private int addressCount;
    private Property[] properties;
    private int propertyCount;
    private Scanner scanner;

    public RealEstateSystem() {
        users = new User[100];
        userCount = 0;
        addresses = new Address[50];
        addressCount = 0;
        properties = new Property[100];
        propertyCount = 0;
        scanner = new Scanner(System.in);
        initializeAddresses();
    }

    private void initializeAddresses() {
        String[][] data = {
                {"Tel Aviv", "Dizengoff"}, {"Tel Aviv", "Rothschild"}, {"Tel Aviv", "Ibn Gvirol"},
                {"Haifa", "Herzl"}, {"Haifa", "HaNassi"}, {"Haifa", "Moriah"},
                {"Jerusalem", "Jaffa"}, {"Jerusalem", "King George"}, {"Jerusalem", "Emek Refaim"},
                {"Rishon LeZion", "Rothschild"}, {"Rishon LeZion", "HaChalutzim"}
        };
        for (String[] pair : data) {
            addresses[addressCount] = new Address(pair[0], pair[1]);
            addressCount++;
        }
    }

    public User[] getUsers() { return users; }
    public int getUserCount() { return userCount; }
    public void setUserCount(int count) { this.userCount = count; }
    public Address[] getAddresses() { return addresses; }
    public int getAddressCount() { return addressCount; }
    public Property[] getProperties() { return properties; }
    public int getPropertyCount() { return propertyCount; }
    public void setPropertyCount(int count) { this.propertyCount = count; }

    // KAN-405: [REQ-003-1] Implement city list extraction
    public String[] getCities() {
        String[] tempCities = new String[addressCount];
        int count = 0;
        for (int i = 0; i < addressCount; i++) {
            String city = addresses[i].getCity();
            boolean exists = false;
            for (int j = 0; j < count; j++) {
                if (tempCities[j].equalsIgnoreCase(city)) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                tempCities[count++] = city;
            }
        }
        String[] uniqueCities = new String[count];
        for (int i = 0; i < count; i++) {
            uniqueCities[i] = tempCities[i];
        }
        return uniqueCities;
    }

    // KAN-409: [REQ-005-1] Implement displayAllProperties()
    public void displayAllProperties() {
        if (propertyCount == 0) {
            System.out.println("No properties found in the system.");
            return;
        }
        for (int i = 0; i < propertyCount; i++) {
            System.out.println((i + 1) + ". " + properties[i]);
        }
    }

    // KAN-408: [REQ-004-1] Implement listing of user-owned properties
    public Property[] getUserProperties(User user) {
        if (user == null) {
            return new Property[0];
        }
        int count = 0;
        for (int i = 0; i < propertyCount; i++) {
            if (properties[i].getPublisher() != null &&
                properties[i].getPublisher().getUsername().equals(user.getUsername())) {
                count++;
            }
        }
        Property[] userProps = new Property[count];
        int index = 0;
        for (int i = 0; i < propertyCount; i++) {
            if (properties[i].getPublisher() != null &&
                properties[i].getPublisher().getUsername().equals(user.getUsername())) {
                userProps[index++] = properties[i];
            }
        }
        return userProps;
    }

    // KAN-399: [REQ-006-1] Implement displayUserProperties(User)
    public void displayUserProperties(User user) {
        if (user == null) {
            System.out.println("Invalid user.");
            return;
        }
        Property[] userProps = getUserProperties(user);
        if (userProps.length == 0) {
            System.out.println("No properties found for user " + user.getUsername() + ".");
            return;
        }
        for (int i = 0; i < userProps.length; i++) {
            System.out.println((i + 1) + ". " + userProps[i]);
        }
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    // KAN-407: [REQ-003-5] Wire user menu: publish property
    public boolean publishProperty(User user) {
        if (user == null) {
            System.out.println("Invalid user.");
            return false;
        }

        int userPropsCount = getUserProperties(user).length;
        int limit = user.isBroker() ? 10 : 3;
        if (userPropsCount >= limit) {
            System.out.println("You have reached the maximum limit of published properties (" + limit + ").");
            return false;
        }

        if (propertyCount >= properties.length) {
            System.out.println("System storage for properties is full.");
            return false;
        }

        String[] cities = getCities();
        System.out.println("Available cities:");
        for (int i = 0; i < cities.length; i++) {
            System.out.println((i + 1) + ". " + cities[i]);
        }
        System.out.print("Choose a city (enter name or number): ");
        String cityInput = scanner.nextLine().trim();
        String selectedCity = null;
        try {
            int cityNum = Integer.parseInt(cityInput);
            if (cityNum >= 1 && cityNum <= cities.length) {
                selectedCity = cities[cityNum - 1];
            }
        } catch (NumberFormatException e) {
            for (String c : cities) {
                if (c.equalsIgnoreCase(cityInput)) {
                    selectedCity = c;
                    break;
                }
            }
        }

        if (selectedCity == null) {
            System.out.println("City does not exist in the system.");
            return false;
        }

        // Find streets in selected city
        String[] tempStreets = new String[addressCount];
        int streetCount = 0;
        for (int i = 0; i < addressCount; i++) {
            if (addresses[i].getCity().equalsIgnoreCase(selectedCity)) {
                String st = addresses[i].getStreet();
                boolean exists = false;
                for (int j = 0; j < streetCount; j++) {
                    if (tempStreets[j].equalsIgnoreCase(st)) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    tempStreets[streetCount++] = st;
                }
            }
        }

        System.out.println("Available streets in " + selectedCity + ":");
        for (int i = 0; i < streetCount; i++) {
            System.out.println((i + 1) + ". " + tempStreets[i]);
        }
        System.out.print("Choose a street (enter name or number): ");
        String streetInput = scanner.nextLine().trim();
        String selectedStreet = null;
        try {
            int streetNum = Integer.parseInt(streetInput);
            if (streetNum >= 1 && streetNum <= streetCount) {
                selectedStreet = tempStreets[streetNum - 1];
            }
        } catch (NumberFormatException e) {
            for (int i = 0; i < streetCount; i++) {
                if (tempStreets[i].equalsIgnoreCase(streetInput)) {
                    selectedStreet = tempStreets[i];
                    break;
                }
            }
        }

        if (selectedStreet == null) {
            System.out.println("Street does not exist in the selected city.");
            return false;
        }

        System.out.println("Property type:");
        System.out.println("1. Regular apartment");
        System.out.println("2. Penthouse");
        System.out.println("3. Private house");
        System.out.print("Choose type (1-3): ");
        int typeChoice;
        try {
            typeChoice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return false;
        }

        String propertyType;
        if (typeChoice == 1) {
            propertyType = "Regular apartment";
        } else if (typeChoice == 2) {
            propertyType = "Penthouse";
        } else if (typeChoice == 3) {
            propertyType = "Private house";
        } else {
            System.out.println("Invalid property type.");
            return false;
        }

        int floor = -1;
        if (typeChoice == 1 || typeChoice == 2) {
            System.out.print("Enter floor: ");
            try {
                floor = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid floor.");
                return false;
            }
        }

        System.out.print("Enter number of rooms: ");
        int rooms;
        try {
            rooms = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number of rooms.");
            return false;
        }

        System.out.print("Enter house number: ");
        int houseNumber;
        try {
            houseNumber = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid house number.");
            return false;
        }

        System.out.print("Is it for rent or sale? (1 - for rent, 2 - for sale): ");
        int dealChoice;
        try {
            dealChoice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
            return false;
        }
        boolean forRent = (dealChoice == 1);

        System.out.print("Enter price ($): ");
        double price;
        try {
            price = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid price.");
            return false;
        }

        Address propAddress = null;
        for (int i = 0; i < addressCount; i++) {
            if (addresses[i].getCity().equalsIgnoreCase(selectedCity) &&
                addresses[i].getStreet().equalsIgnoreCase(selectedStreet)) {
                propAddress = addresses[i];
                break;
            }
        }
        if (propAddress == null) {
            propAddress = new Address(selectedCity, selectedStreet);
        }

        Property newProperty = new Property(propAddress, user, propertyType, rooms, houseNumber, floor, forRent, price);
        properties[propertyCount++] = newProperty;
        System.out.println("Property published successfully!");
        return true;
    }

    // KAN-410: [REQ-007-1] Implement search input with sentinel -999
    public Property[] searchProperties() {
        System.out.print("Enter deal type (-999 for all, 1 for rent, 2 for sale): ");
        int forRentFlag = -999;
        try {
            forRentFlag = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            forRentFlag = -999;
        }

        System.out.print("Enter property type (-999 for all, 1 for Regular apartment, 2 for Penthouse, 3 for Private house): ");
        int typeChoice = -999;
        try {
            typeChoice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            typeChoice = -999;
        }

        System.out.print("Enter number of rooms (-999 for all): ");
        int roomsChoice = -999;
        try {
            roomsChoice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            roomsChoice = -999;
        }

        System.out.print("Enter minimum price (-999 for none): ");
        double minPrice = -999;
        try {
            minPrice = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            minPrice = -999;
        }

        System.out.print("Enter maximum price (-999 for none): ");
        double maxPrice = -999;
        try {
            maxPrice = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            maxPrice = -999;
        }

        String expectedType = null;
        if (typeChoice == 1) expectedType = "Regular apartment";
        else if (typeChoice == 2) expectedType = "Penthouse";
        else if (typeChoice == 3) expectedType = "Private house";

        int matchCount = 0;
        for (int i = 0; i < propertyCount; i++) {
            Property p = properties[i];
            if (forRentFlag != -999) {
                if (forRentFlag == 1 && !p.isForRent()) continue;
                if (forRentFlag == 2 && p.isForRent()) continue;
            }
            if (typeChoice != -999 && expectedType != null) {
                if (!p.getPropertyType().equalsIgnoreCase(expectedType)) continue;
            }
            if (roomsChoice != -999) {
                if (p.getRooms() != roomsChoice) continue;
            }
            if (minPrice != -999) {
                if (p.getPrice() < minPrice) continue;
            }
            if (maxPrice != -999) {
                if (p.getPrice() > maxPrice) continue;
            }
            matchCount++;
        }

        Property[] results = new Property[matchCount];
        int index = 0;
        for (int i = 0; i < propertyCount; i++) {
            Property p = properties[i];
            if (forRentFlag != -999) {
                if (forRentFlag == 1 && !p.isForRent()) continue;
                if (forRentFlag == 2 && p.isForRent()) continue;
            }
            if (typeChoice != -999 && expectedType != null) {
                if (!p.getPropertyType().equalsIgnoreCase(expectedType)) continue;
            }
            if (roomsChoice != -999) {
                if (p.getRooms() != roomsChoice) continue;
            }
            if (minPrice != -999) {
                if (p.getPrice() < minPrice) continue;
            }
            if (maxPrice != -999) {
                if (p.getPrice() > maxPrice) continue;
            }
            results[index++] = p;
        }

        if (results.length == 0) {
            System.out.println("No matching properties found.");
        } else {
            System.out.println("Search results (" + results.length + " properties found):");
            for (int i = 0; i < results.length; i++) {
                System.out.println((i + 1) + ". " + results[i]);
            }
        }

        return results;
    }

    // REQ-004: removeProperty(User user)
    public void removeProperty(User user) {
        if (user == null) {
            System.out.println("Invalid user.");
            return;
        }
        Property[] userProps = getUserProperties(user);
        if (userProps.length == 0) {
            System.out.println("You have not published any properties.");
            return;
        }

        System.out.println("Your properties:");
        for (int i = 0; i < userProps.length; i++) {
            System.out.println((i + 1) + ". " + userProps[i]);
        }

        System.out.print("Choose property number to remove: ");
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (choice < 1 || choice > userProps.length) {
            System.out.println("Invalid selection.");
            return;
        }

        Property toRemove = userProps[choice - 1];
        int targetIndex = -1;
        for (int i = 0; i < propertyCount; i++) {
            if (properties[i] == toRemove) {
                targetIndex = i;
                break;
            }
        }

        if (targetIndex != -1) {
            for (int i = targetIndex; i < propertyCount - 1; i++) {
                properties[i] = properties[i + 1];
            }
            properties[propertyCount - 1] = null;
            propertyCount--;
            System.out.println("Property removed successfully!");
        }
    }

    // REQ-001: createUser()
    public void createUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        while (isUsernameTaken(username)) {
            System.out.println("Username is already taken.");
            System.out.print("Enter username: ");
            username = scanner.nextLine().trim();
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();
        while (!isStrongPassword(password)) {
            System.out.println("Weak password. Must contain at least one digit and at least one of ($, %, _).");
            System.out.print("Enter password: ");
            password = scanner.nextLine().trim();
        }

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine().trim();
        while (!isValidPhone(phone)) {
            System.out.println("Invalid phone number. Must be 10 digits and start with 05.");
            System.out.print("Enter phone number: ");
            phone = scanner.nextLine().trim();
        }

        System.out.print("Are you a real estate broker? (1 - Yes, 2 - No): ");
        String brokerChoice = scanner.nextLine().trim();
        boolean isBroker = brokerChoice.equals("1") || brokerChoice.equalsIgnoreCase("yes");

        User newUser = new User(username, password, phone, isBroker);
        users[userCount++] = newUser;
        System.out.println("Account created successfully!");
    }

    // REQ-002: login()
    public User login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        for (int i = 0; i < userCount; i++) {
            if (users[i].getUsername().equals(username) && users[i].checkPassword(password)) {
                return users[i];
            }
        }
        return null;
    }

    // Validation helper methods
    public static boolean isStrongPassword(String pass) {
        if (pass == null || pass.isEmpty()) return false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;
        for (char c : pass.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (c == '$' || c == '%' || c == '_') {
                hasSpecialChar = true;
            }
        }
        return hasDigit && hasSpecialChar;
    }

    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.length() != 10) return false;
        if (!phone.startsWith("05")) return false;
        for (char c : phone.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    public boolean isUsernameTaken(String username) {
        if (username == null) return false;
        for (int i = 0; i < userCount; i++) {
            if (users[i].getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }
}