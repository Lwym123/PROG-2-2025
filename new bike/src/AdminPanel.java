import java.util.Scanner;

// Admin panel with log viewing and queue management
public class AdminPanel {
    private static final Scanner scanner = new Scanner(System.in);
    private static final BikeService bikeService = new BikeService();
    private static final UserService userService = new UserService();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== ADMIN PANEL =====");
            System.out.println("1. Start User Rental System");
            System.out.println("2. View All Registered Users");
            System.out.println("3. View System Logs");
            System.out.println("4. Manage Bike Request Queue");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    new BikeRental(bikeService, userService).startRentalSystem();
                    break;
                case 2:
                    userService.viewAllUsers();
                    break;
                case 3:
                    bikeService.viewSystemLogs();
                    break;
                case 4:
                    manageRentalQueue();
                    break;
                case 5:
                    System.out.println("Exiting system...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Submenu for managing bike rental queue
    private static void manageRentalQueue() {
        while (true) {
            System.out.println("\n===== QUEUE MANAGEMENT =====");
            System.out.println("1. View All Pending Requests");
            System.out.println("2. Process First Request");
            System.out.println("3. Back to Admin Menu");
            System.out.print("Enter choice: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                bikeService.viewRequestQueue();
            } else if (option == 2) {
                bikeService.processFirstRequest();
            } else if (option == 3) {
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
    }
}