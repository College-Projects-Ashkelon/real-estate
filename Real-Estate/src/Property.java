public class Property {
    private Address address;
    private User publisher;
    private String propertyType;
    private int rooms;
    private int houseNumber;
    private int floor;
    private boolean forRent;
    private double price;

    public Property(Address address, User publisher, String propertyType, int rooms,
                    int houseNumber, int floor, boolean forRent, double price) {
        this.address = address;
        this.publisher = publisher;
        this.propertyType = propertyType;
        this.rooms = rooms;
        this.houseNumber = houseNumber;
        this.floor = floor;
        this.forRent = forRent;
        this.price = price;
    }

    public Address getAddress() { return address; }
    public User getPublisher() { return publisher; }
    public String getPropertyType() { return propertyType; }
    public int getRooms() { return rooms; }
    public int getHouseNumber() { return houseNumber; }
    public int getFloor() { return floor; }
    public boolean isForRent() { return forRent; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        String dealType = forRent ? "for rent" : "for sale";
        String priceFormatted = String.format("%,.0f", price);
        String floorInfo = propertyType.equals("Private house")
                ? "house no. " + houseNumber
                : "floor " + floor;
        String role = publisher.isBroker() ? "real estate broker" : "owner";

        return propertyType + " -- " + dealType + ": " + rooms + " rooms, " + floorInfo + ".\n" +
                "Price: " + priceFormatted + "$.\n" +
                "Contact info: " + publisher.getUsername() + " " + publisher.getPhone() + " (" + role + ").";
    }
}