import java.time.LocalDateTime;
import java.util.ArrayList;

public class RentalService {
    private ArrayList<ActiveRental> activeRentals;
    
    // 基础车费常量
    private static final double BASE_FARE = 3.0;
    
    public RentalService() {
        activeRentals = new ArrayList<>();
    }
    
    /**
     * Start a rental
     */
    public void startRental(String bikeID, String userEmail) {
        LocalDateTime tripStartTime = LocalDateTime.now();
        ActiveRental activeRental = new ActiveRental(bikeID, userEmail, tripStartTime);
        activeRentals.add(activeRental);
        System.out.println("Rental started for bike: " + bikeID);
    }
    
    /**
     * End a rental（带用户对象，可计算车费）
     */
    public void endRental(String bikeID, RegisteredUsers user) {
        for (int i = 0; i < activeRentals.size(); i++) {
            ActiveRental rental = activeRentals.get(i);
            if (rental.getBikeID().equals(bikeID)) {
                activeRentals.remove(i);
                
                // 计算车费（多态调用）
                double fare = user.calculateFare(BASE_FARE);
                System.out.println("Rental ended for bike: " + bikeID);
                System.out.println("Trip fare: $" + fare);
                
                // 显示用户类型和折扣信息
                System.out.print("User type: ");
                user.displayUserType();
                if (user instanceof VIPUser) {
                    System.out.println("VIP discount applied! (20% off)");
                }
                return;
            }
        }
        System.out.println("No active rental found for bike: " + bikeID);
    }
    
    /**
     * End a rental（无用户对象版本，保留兼容）
     */
    public void endRental(String bikeID) {
        for (int i = 0; i < activeRentals.size(); i++) {
            ActiveRental rental = activeRentals.get(i);
            if (rental.getBikeID().equals(bikeID)) {
                activeRentals.remove(i);
                System.out.println("Rental ended for bike: " + bikeID);
                System.out.println("(Fare not calculated - user object not provided)");
                return;
            }
        }
        System.out.println("No active rental found for bike: " + bikeID);
    }
    
    /**
     * Cancel a rental
     */
    public void cancelRental(String bikeID) {
        for (int i = 0; i < activeRentals.size(); i++) {
            ActiveRental rental = activeRentals.get(i);
            if (rental.getBikeID().equals(bikeID)) {
                activeRentals.remove(i);
                System.out.println("Rental cancelled for bike: " + bikeID);
                return;
            }
        }
        System.out.println("No active rental found for bike: " + bikeID);
    }
    
    /**
     * View all active rentals
     */
    public void viewActiveRentals() {
        if (activeRentals.isEmpty()) {
            System.out.println("No active rentals at this time.");
        } else {
            System.out.println("=== Active Rentals ===");
            for (ActiveRental rental : activeRentals) {
                System.out.println(rental);
            }
        }
    }
    
    /**
     * Check if a bike is currently rented
     */
    public boolean isBikeRented(String bikeID) {
        for (ActiveRental rental : activeRentals) {
            if (rental.getBikeID().equals(bikeID)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Get the bike currently rented by a user
     */
    public String getUserCurrentRental(String userEmail) {
        for (ActiveRental rental : activeRentals) {
            if (rental.getUserEmail().equals(userEmail)) {
                return rental.getBikeID();
            }
        }
        return null;
    }
    
    public ArrayList<ActiveRental> getActiveRentals() {
        return activeRentals;
    }
}