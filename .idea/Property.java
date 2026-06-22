public class Property {
    private Address address;
    private int rooms;
    private double price;
    private String type;
    private boolean isRent;
    private int houseNumber;
    private int floor;
    private User publisher;

    public Property(Address address, int rooms, double price, String type, boolean isRent, int houseNumber, int floor, User publisher) {
        this.address = address;
        this.rooms = rooms;
        this.price = price;
        this.type = type;
        this.isRent = isRent;
        this.houseNumber = houseNumber;
        this.floor = floor;
        this.publisher = publisher;
    }

    public Address getAddress() {
        return address;
    }

    public int getRooms() {
        return rooms;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public boolean isRent() {
        return isRent;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public int getFloor() {
        return floor;
    }

    public User getPublisher() {
        return publisher;
    }

    @Override
    public String toString() {
        return type + " - " + (isRent ? "For Rent" : "For Sale") + ": " + address.toString() + " " + houseNumber + ", floor " + floor + ". " + rooms + " rooms. Price: " + price + "$. Published by: " + publisher.getUsername();
    }
}