import java.time.LocalDateTime;
import java.util.ArrayList;

public class RentalService {
    private ArrayList<ActiveRental> activeRentals;
    
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
     * End a rental
     */
    public void endRental(String bikeID) {
        for (int i = 0; i < activeRentals.size(); i++) {
            ActiveRental rental = activeRentals.get(i);
            if (rental.getBikeID().equals(bikeID)) {
                activeRentals.remove(i);
                System.out.println("Rental ended for bike: " + bikeID);
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