import java.util.Scanner;

public class AdminPanel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BikeRental bikeRental = new BikeRental();
        
        boolean running = true;
        while (running) {
            System.out.println("\n=== Admin Panel ===");
            System.out.println("1. Demo the Bike Rental System");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    bikeRental.simulateApplication();
                    break;
                case 2:
                    running = false;
                    System.out.println("Exiting Admin Panel...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        
        scanner.close();
    }
}