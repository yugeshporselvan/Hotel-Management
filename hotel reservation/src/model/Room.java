package model;

public class Room implements IRoom{
    private final String roomNumber;
    private final Double roomPrice;
    private  RoomType roomType;
    public Room(String roomNumber,Double roomPrice,RoomType roomType){
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.roomType = roomType;

    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return roomPrice;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean isFree() {
        return roomPrice == 0;
    }
    @Override
    public String toString() {
        return "Room Number: "
                + roomNumber + ", " +
                "Price: $" + roomPrice + ", Type: " + roomType;
    }
}
