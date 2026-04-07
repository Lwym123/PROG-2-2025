import java.util.Scanner;

// User interface for bike rental operations
public class BikeRental {
    private final Scanner scanner = new Scanner(System.in);
    private final BikeService bikeService;
    private final UserService userService;
    private final RentalService rentalService;

    public BikeRental(BikeService bikeService, UserService userService) {
        this.bikeService = bikeService;
        this.userService = userService;
        this.rentalService = new RentalService();
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
        
        System.out.print("Do you want to (1) Rent a bike or (2) Return a bike? ");
        int action = scanner.nextInt();
        scanner.nextLine();
        
        if (action == 1) {
            // 租车
            bikeService.reserveBike(location, email, rentalService, userService);
        } else if (action == 2) {
            // 还车
            System.out.print("Enter bike ID to return: ");
            String bikeID = scanner.nextLine();
            
            // 获取用户对象用于计算车费
            RegisteredUsers user = userService.getUserByEmail(email);
            
            // 结束租车并计算车费
            rentalService.endRental(bikeID, user);
            
            // 将自行车标记为可用
            bikeService.markBikeAsAvailable(bikeID);
            bikeService.addLog("Bike returned: ID=" + bikeID + ", User=" + email);
        } else {
            System.out.println("Invalid option.");
        }
    }
}
