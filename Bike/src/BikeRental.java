import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

public class BikeRental {
    private boolean isRegisteredUser;
    private String emailAddress;
    private String location;
    private LocalDateTime tripStartTime;
    
    public BikeRental() {
    }
    
    private String analyseRequest() {
        if (isRegisteredUser) {
            System.out.println("Welcome back, " + emailAddress + "!");
        } else {
            System.out.println("You're not our registered user. Please consider registering.");
            UserRegistration.registration();
        }
        return validate(location);
    }
    
    private String validateLocation(String location) {
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getLocation().equals(location) && bike.isAvailable()) {
                System.out.println("A bike is available at the location you requested.");
                return bike.getBikeID();
            }
        }
        System.out.println("Sorry, no bikes are available at the location you requested. Please try again later.");
        return null;
    }
    
    private void reserveBike(String bikeID) {
        if (bikeID != null) {
            for (Bike bike : BikeDatabase.bikes) {
                if (bike.getBikeID().equals(bikeID)) {
                    tripStartTime = LocalDateTime.now();
                    bike.setAvailable(false);
                    bike.setLastUsedTime(tripStartTime);
                    System.out.println("Reserving the bike with the " + bikeID + 
                                     ". Please following the on-screen instructions to locate the bike and start your pleasant journey.");
                    ActiveRental activeRental = new ActiveRental(bikeID, emailAddress, tripStartTime);
                    // activeRentalList.add(activeRental); // activeRentalList needs to be added as class variable
                    break;
                }
            }
        } else {
            System.out.println("Sorry, we're unable to reserve a bike at this time. Please try again later.");
        }
    }
    
    private void viewActiveRentals() {
        // activeRentalList needs to be added as class variable
    }
    
    private void removeTrip(String bikeID) {
        // activeRentalList needs to be added as class variable
    }
    
    public void simulateApplication() {
        // This method will be called from AdminPanel
    }
}