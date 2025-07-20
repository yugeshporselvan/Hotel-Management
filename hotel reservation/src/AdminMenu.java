import api.AdminResource;
import model.Customer;
import model.IRoom;
import java.util.*;

public class AdminMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final AdminResource adminResource = AdminResource.getInstance();

    public static void runAdminMenu() {
        String option;
        do {
            printAdminMenu();
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    seeAllCustomers();
                    break;
                case "2":
                    seeAllRooms();
                    break;
                case "3":
                    seeAllReservations();
                    break;
                case "4":
                    addRoom();
                    break;
                case "5":
                    System.out.println("Returning Back to Main Menu.");
                    return;
                default:
                    System.out.println("Wrong choice, please try again.");
            }
        } while (!option.equals("5"));
    }

    private static void printAdminMenu() {
        System.out.println("\nAdmin Menu");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
        System.out.print("Select an option: ");
    }

    private static void seeAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }
    }

    private static void seeAllRooms() {
        Collection<IRoom> rooms = adminResource.getAllRooms();
        if (rooms.isEmpty()) {
            System.out.println("No rooms available.");
        } else {
            for (IRoom room : rooms) {
                System.out.println(room);
            }
        }
    }

    private static void seeAllReservations() {
        adminResource.displayAllReservations();
    }

    private static void addRoom() {
        List<IRoom> rooms = new ArrayList<>();
        while (true) {
                        System.out.print("Enter room number: ");
            String roomNumber = scanner.nextLine();
            System.out.print("Enter room price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter room type (SINGLE/DOUBLE): ");
            String roomType = scanner.nextLine().toUpperCase();

            IRoom newRoom = new model.Room(roomNumber, price, model.RoomType.valueOf(roomType));
            rooms.add(newRoom);

            System.out.print("Would you like to add another room? (yes/no): ");
            if (!scanner.nextLine().equalsIgnoreCase("yes")) {
                break;
            }
        }

        adminResource.addRoom(rooms);
        System.out.println("Rooms added successfully");
    }
}
