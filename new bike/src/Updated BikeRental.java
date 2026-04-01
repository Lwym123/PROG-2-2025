import java.util.Scanner;

public class BikeRental {
    private UserService userService;
    private BikeService bikeService;
    private RentalService rentalService;
    
    public BikeRental() {
        userService = new UserService();
        bikeService = new BikeService();
        rentalService = new RentalService();
    }
    
    /**
     * Process a rental request
     */
    public void processRentalRequest(String email, String location) {
        // 1. Check if user is registered
        if (!userService.isRegisteredUser(email)) {
            System.out.println("You're not our registered user. Please consider registering.");
            userService.registerNewUser();
            // After registration, check again
            if (!userService.isRegisteredUser(email)) {
                System.out.println("Registration failed. Please try again later.");
                return;
            }
        } else {
            System.out.println("Welcome back, " + email + "!");
        }
        
        // 2. Find available bike
        String bikeID = bikeService.findAvailableBike(location);
        if (bikeID != null) {
            // 3. Reserve the bike
            bikeService.reserveBike(bikeID);
            // 4. Start rental record
            rentalService.startRental(bikeID, email);
        }
    }
    
    /**
     * End a rental
     */
    public void endRental(String bikeID) {
        rentalService.endRental(bikeID);
        bikeService.releaseBike(bikeID);
    }
    
    /**
     * View active rentals
     */
    public void viewActiveRentals() {
        rentalService.viewActiveRentals();
    }
    
    /**
     * View all users
     */
    public void viewUsers() {
        userService.viewAllUsers();
    }
    
    /**
     * Simulate the main application flow
     */
    public void simulateApplication() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            System.out.println("\n=== Bike Rental System ===");
            System.out.println("1. Rent a bike");
            System.out.println("2. End a rental");
            System.out.println("3. View active rentals");
            System.out.println("4. View registered users");
            System.out.println("5. Register new user");
            System.out.println("6. Return to Admin Panel");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    System.out.print("Enter email address: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter location: ");
                    String location = scanner.nextLine();
                    processRentalRequest(email, location);
                    break;
                case 2:
                    System.out.print("Enter bike ID to end rental: ");
                    String bikeID = scanner.nextLine();
                    endRental(bikeID);
                    break;
                case 3:
                    viewActiveRentals();
                    break;
                case 4:
                    viewUsers();
                    break;
                case 5:
                    userService.registerNewUser();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}