import java.time.LocalDateTime;

// POJO class for bike rental queue requests
public class BikeRequest {
    private final String userEmail;
    private final String requestLocation;
    private final LocalDateTime requestTime;

    public BikeRequest(String userEmail, String requestLocation) {
        this.userEmail = userEmail;
        this.requestLocation = requestLocation;
        this.requestTime = LocalDateTime.now();
    }

    // Getter methods
    public String getUserEmail() {
        return userEmail;
    }

    public String getRequestLocation() {
        return requestLocation;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    @Override
    public String toString() {
        return "User: " + userEmail +
                " | Location: " + requestLocation +
                " | Request Time: " + requestTime;
    }
}