public class Bike {
    private final String bikeId;
    private final String location;
    private boolean available;

    public Bike(String bikeId, String location) {
        this.bikeId = bikeId;
        this.location = location;
        this.available = true;
    }

    // Getters and Setters
    public String getBikeId() {
        return bikeId;
    }

    public String getLocation() {
        return location;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}