package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private static final HotelResource instance = new HotelResource();

    private HotelResource() {}
    public static HotelResource getInstance() {
        return instance;
    }
    public Customer getCustomer(String email) {
        return CustomerService.getInstance().getCustomer(email);
    }
    public void createACustomer(String email, String firstName, String lastName) {
        CustomerService.getInstance().addCustomer(email, firstName, lastName);
    }
    public IRoom getRoom(String roomNumber) {
        return ReservationService.getInstance().getARoom(roomNumber);
    }
    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer customer = getCustomer(customerEmail);
        if (customer == null) {
            System.out.println("Customer not found!");
            return null;
        }
        return ReservationService.getInstance().reserveARoom(customer, room, checkInDate, checkOutDate);
    }
    public Collection<Reservation> getCustomersReservations(String customerEmail) {
        Customer customer = getCustomer(customerEmail);
        if (customer != null) {
            return ReservationService.getInstance().getCustomersReservation(customer);
        }
        return null;
    }
    public Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate) {
        return ReservationService.getInstance().findRooms(checkInDate, checkOutDate);
    }
}
