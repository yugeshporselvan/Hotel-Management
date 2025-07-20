package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import java.util.*;

public class ReservationService {
    private static final ReservationService instance = new ReservationService();
    private final Map<String, IRoom> rooms = new HashMap<>();
    private final List<Reservation> reservations = new ArrayList<>();

    private ReservationService() {}

    public static ReservationService getInstance() {
        return instance;
    }

    public void addRoom(IRoom room) {
        try {
            rooms.put(room.getRoomNumber(), room);
        } catch (Exception e) {
            System.out.println("error adding room: " + e.getMessage());
        }
    }
    public IRoom getARoom(String roomId) {
        try {
            if (!rooms.containsKey(roomId)) {
                throw new NoSuchElementException("room not found: " + roomId);
            }
            return rooms.get(roomId);
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
            return null;
        }
    }
    public Collection<IRoom> getAllRooms() {
        return rooms.values();
    }
    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        try {
            if (customer == null) {
                throw new IllegalArgumentException("Customer cannot be null.");
            }
            if (room == null) {
                throw new IllegalArgumentException("Room cannot be null.");
            }
            Reservation newReservation = new Reservation(customer, room, checkInDate, checkOutDate);
            reservations.add(newReservation);
            return newReservation;
        } catch (Exception e) {
            System.out.println("Error reserving room:" + e.getMessage());
            return null;
        }
    }
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Collection<IRoom> availableRooms = new ArrayList<>(rooms.values());

        for (Reservation reservation : reservations) {
            Date reservedCheckIn = reservation.getCheckInDate();
            Date reservedCheckOut = reservation.getCheckOutDate();

            // Remove booked rooms
            if (!(checkOutDate.before(reservedCheckIn) || checkInDate.after(reservedCheckOut))) {
                availableRooms.remove(reservation.getRoom());
            }
        }
        return availableRooms;
    }
    public Collection<Reservation> getCustomersReservation(Customer customer) {
        List<Reservation> customerReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().equals(customer)) {
                customerReservations.add(reservation);
            }
        }
        return customerReservations;
    }
    public void printAllReservation() {
        try {
            if (reservations.isEmpty()) {
                System.out.println("No reservations found.");
            } else {
                for (Reservation reservation : reservations) {
                    System.out.println(reservation);
                }
            }
        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
        }
    }
}
