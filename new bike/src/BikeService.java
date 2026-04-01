import java.time.LocalDateTime;

public class BikeService {
    
    /**
     * Find an available bike at the specified location
     */
    public String findAvailableBike(String location) {
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getLocation().equals(location) && bike.isAvailable()) {
                System.out.println("A bike is available at the location you requested.");
                return bike.getBikeID();
            }
        }
        System.out.println("Sorry, no bikes are available at the location you requested. Please try again later.");
        return null;
    }
    
    /**
     * Validate if a location exists
     */
    public boolean validateLocation(String location) {
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getLocation().equals(location)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Reserve a bike
     */
    public void reserveBike(String bikeID) {
        if (bikeID != null) {
            for (Bike bike : BikeDatabase.bikes) {
                if (bike.getBikeID().equals(bikeID)) {
                    bike.setAvailable(false);
                    bike.setLastUsedTime(LocalDateTime.now());
                    System.out.println("Reserving the bike with ID " + bikeID + 
                                     ". Please follow the on-screen instructions to locate the bike and start your pleasant journey.");
                    break;
                }
            }
        } else {
            System.out.println("Sorry, we're unable to reserve a bike at this time. Please try again later.");
        }
    }
    
    /**
     * Release a bike (return)
     */
    public void releaseBike(String bikeID) {
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equals(bikeID)) {
                bike.setAvailable(true);
                System.out.println("Bike " + bikeID + " has been released.");
                break;
            }
        }
    }
    
    /**
     * Get bike information
     */
    public Bike getBike(String bikeID) {
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equals(bikeID)) {
                return bike;
            }
        }
        return null;
    }
}