package model;

public class FreeRoom extends Room{
    public FreeRoom(String roomNumber,RoomType roomType){
        super(roomNumber,0.0, roomType);
    }
    @Override
    public Double getRoomPrice(){
        return 0.0;
    }
    public String toString() {
        return super.toString() + " (Free Room)" +"$";
    }

}
