public class RealEstateSystem {
    private User[] users;
    private int userCount;
    private Address[] addresses;
    private int addressCount;
    private Property[] properties;
    private int propertyCount;

    public RealEstateSystem() {
        users = new User[100];
        userCount = 0;
        addresses = new Address[50];
        addressCount = 0;
        properties = new Property[100];
        propertyCount = 0;
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
}