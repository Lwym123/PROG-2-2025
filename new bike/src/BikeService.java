import java.util.*;

// Manages bikes, logs (STACK), and rental requests (QUEUE)
public class BikeService {
    // Stack for system logs (REQUIRED BY PDF)
    private final Stack<ERyderLog> logStack = new Stack<>();
    // Queue for bike rental requests (REQUIRED BY PDF)
    private final Queue<BikeRequest> bikeRequestQueue = new ArrayDeque<>();

    private final List<Bike> bikes = BikeDatabase.bikes;
    private int logSequence = 1;

    // Reserve a bike; add to queue if no bikes available
    public Bike reserveBike(String location, String userEmail) {
        Bike availableBike = findAvailableBike(location);

        if (availableBike != null) {
            availableBike.setAvailable(false);
            addLog("Bike rented: ID=" + availableBike.getBikeId() + ", User=" + userEmail);
            return availableBike;
        } else {
            // No bikes available → add to queue
            bikeRequestQueue.add(new BikeRequest(userEmail, location));
            System.out.println("No bikes available. Added to waiting queue.");
            return null;
        }
    }

    // Release bike and auto-assign to queue if requests exist
    public void releaseBike(Bike bike) {
        bike.setAvailable(true);
        addLog("Trip finished, bike returned: ID=" + bike.getBikeId());

        // Auto process next request in queue
        if (!bikeRequestQueue.isEmpty()) {
            BikeRequest nextRequest = bikeRequestQueue.poll();
            System.out.println("Auto-assigned bike to queued user: " + nextRequest.getUserEmail());
            reserveBike(nextRequest.getRequestLocation(), nextRequest.getUserEmail());
        }
    }

    // Add event to log stack
    public void addLog(String event) {
        logStack.push(new ERyderLog("LOG-" + logSequence++, event));
    }

    // View all system logs (loop through stack)
    public void viewSystemLogs() {
        System.out.println("\n===== SYSTEM LOGS =====");
        if (logStack.isEmpty()) {
            System.out.println("No logs available.");
            return;
        }
        for (ERyderLog log : logStack) {
            System.out.println(log);
        }
    }

    // View all pending rental requests
    public void viewRequestQueue() {
        System.out.println("\n===== PENDING BIKE REQUESTS =====");
        if (bikeRequestQueue.isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        for (BikeRequest request : bikeRequestQueue) {
            System.out.println(request);
        }
    }

    // Admin: manually process first request
    public void processFirstRequest() {
        if (!bikeRequestQueue.isEmpty()) {
            BikeRequest processed = bikeRequestQueue.poll();
            System.out.println("Processed request: " + processed.getUserEmail());
        } else {
            System.out.println("No requests in queue.");
        }
    }

    // Find available bike by location
    private Bike findAvailableBike(String location) {
        for (Bike bike : bikes) {
            if (bike.getLocation().equalsIgnoreCase(location) && bike.isAvailable()) {
                return bike;
            }
        }
        return null;
    }

    // Getters
    public Queue<BikeRequest> getBikeRequestQueue() {
        return bikeRequestQueue;
    }
}