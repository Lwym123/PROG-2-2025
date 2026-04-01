import java.util.Scanner;

public class AdminPanel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BikeRental bikeRental = new BikeRental();
        UserService userService = new UserService();
        
        boolean running = true;
        while (running) {
            System.out.println("\n=== Admin Panel ===");
            System.out.println("1. Demo the Bike Rental System");
            System.out.println("2. Manage Users");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    bikeRental.simulateApplication();
                    break;
                case 2:
                    manageUsers(scanner, userService);
                    break;
                case 3:
                    running = false;
                    System.out.println("Exiting Admin Panel...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        
        scanner.close();
    }
    
    /**
     * User management sub-menu
     */
    private static void manageUsers(Scanner scanner, UserService userService) {
        boolean managing = true;
        while (managing) {
            System.out.println("\n=== User Management ===");
            System.out.println("1. View all users");
            System.out.println("2. Add user");
            System.out.println("3. Remove user");
            System.out.println("4. Update user");
            System.out.println("5. Back to main menu");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    userService.viewAllUsers();
                    break;
                case 2:
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    userService.addUser(email, name);
                    break;
                case 3:
                    System.out.print("Enter email to remove: ");
                    email = scanner.nextLine();
                    userService.removeUser(email);
                    break;
                case 4:
                    System.out.print("Enter email to update: ");
                    email = scanner.nextLine();
                    System.out.print("Enter new name: ");
                    name = scanner.nextLine();
                    userService.updateUser(email, name);
                    break;
                case 5:
                    managing = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}