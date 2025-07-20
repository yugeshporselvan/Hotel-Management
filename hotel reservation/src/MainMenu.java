import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import java.util.*;

public class MainMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final HotelResource hotelResource = HotelResource.getInstance();

    public static void main(String[] args) {
        runMainMenu();
    }

    private static void runMainMenu() {
        String option;
        do {
            printMainMenu();
            option = scanner.nextLine();
            try{
            switch (option) {
                case "1":
                    findAndReserveRoom();
                    break;
                case "2":
                    seeMyReservations();
                    break;
                case "3":
                    createAccount();
                    break;
                case "4":
                    AdminMenu.runAdminMenu();
                    break;
                case "5":
                    System.out.println("Exiting application. Thank-you Visit Again");
                    break;
                default:
                    System.out.println("wrong choice, please try again.");
            }
            }catch (Exception e){
                System.out.println(" error : " + e.getMessage());
            }
        } while (!option.equals("5"));
    }

    private static void printMainMenu() {
        System.out.println("\nMain Menu");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.print("Select an option: ");
    }

    private static void findAndReserveRoom() {
        try {
            System.out.print("Enter check-in date (yyyy-MM-dd): ");
            Date checkIn = getInputDate();
            System.out.print("Enter check-out date (yyyy-MM-dd): ");
            Date checkOut = getInputDate();

            Collection<IRoom> availableRooms = hotelResource.findARoom(checkIn, checkOut);
            if (availableRooms.isEmpty()) {
                System.out.println("No available rooms for the given dates.");
                return;
            }

            System.out.println("Available Rooms:");
            for (IRoom room : availableRooms) {
                System.out.println(room);
            }

            System.out.print("Enter the room number to book: ");
            String roomNumber = scanner.nextLine();
            IRoom room = hotelResource.getRoom(roomNumber);
            if (room == null) {
                System.out.println("Wrong room number.");
                return;
            }

            System.out.print("Enter your email: ");
            String email = scanner.nextLine();
            Reservation reservation = hotelResource.bookARoom(email, room, checkIn, checkOut);
            if (reservation != null) {
                System.out.println("Reservation successful");
                System.out.println(reservation);
            }
        }catch (Exception e){
            System.out.println("error: " + e.getMessage());
        }
    }

    private static void seeMyReservations() {
        try {
            System.out.print("Enter your email: ");
            String email = scanner.nextLine();
            Collection<Reservation> reservations = hotelResource.getCustomersReservations(email);
            if (reservations == null || reservations.isEmpty()) {
                throw new NoSuchElementException("No reservations found.");
            }
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void createAccount() {
        try {
            String email;
            boolean isValidEmail;
            do {
                System.out.print("Enter email: ");
                email = scanner.nextLine().toLowerCase().trim();

                isValidEmail = email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
                if (!isValidEmail) {
                    System.out.println("Invalid email format. Please enter a valid email.");
                }
            } while (!isValidEmail);

            System.out.print("Enter first name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter last name: ");
            String lastName = scanner.nextLine();
            hotelResource.createACustomer(email, firstName, lastName);
            System.out.println("Account created successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private static Date getInputDate() {
        while (true) {
            try {
                return new Scanner(System.in).nextLine().matches("\\d{4}-\\d{2}-\\d{2}") ? new Date() : null;
            } catch (Exception e) {
                System.out.println("Wrong Date Pattern. Please enter the date in yyyy-MM-dd format.");
            }
        }
    }
}
