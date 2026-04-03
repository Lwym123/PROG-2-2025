import java.util.Scanner;

// User interface for bike rental operations
public class BikeRental {
    private final Scanner scanner = new Scanner(System.in);
    private final BikeService bikeService;
    private final UserService userService;

    public BikeRental(BikeService bikeService, UserService userService) {
        this.bikeService = bikeService;
        this.userService = userService;
    }

    public void startRentalSystem() {
        System.out.print("\nEnter your email: ");
        String email = scanner.nextLine();

        if (!userService.isRegisteredUser(email)) {
            System.out.println("Error: User not registered.");
            return;
        }

        System.out.print("Enter rental location: ");
        String location = scanner.nextLine();

        bikeService.reserveBike(location, email);
    }
}