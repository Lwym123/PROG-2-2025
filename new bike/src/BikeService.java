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
    // 修改：增加 rentalService 和 userService 参数
    public Bike reserveBike(String location, String userEmail, RentalService rentalService, UserService userService) {
        Bike availableBike = findAvailableBike(location);

        if (availableBike != null) {
            availableBike.setAvailable(false);
            
            // 开始租车记录
            rentalService.startRental(availableBike.getBikeId(), userEmail);
            
            addLog("Bike rented: ID=" + availableBike.getBikeId() + ", User=" + userEmail);
            System.out.println("Bike " + availableBike.getBikeId() + " rented successfully!");
            
            // 显示用户类型
            RegisteredUsers user = userService.getUserByEmail(userEmail);
            if (user != null) {
                System.out.print("User type: ");
                user.displayUserType();
            }
            
            return availableBike;
        } else {
            // No bikes available → add to queue
            bikeRequestQueue.add(new BikeRequest(userEmail, location));
            System.out.println("No bikes available at " + location + ". Added to waiting queue.");
            return null;
        }
    }

    // Release bike and auto-assign to queue if requests exist
    // 修改：增加 rentalService 和 userService 参数，用于计算车费
    public void releaseBike(Bike bike, RentalService rentalService, UserService userService) {
        // 获取租车的用户
        String userEmail = getRentalUserEmail(bike.getBikeId(), rentalService);
        
        bike.setAvailable(true);
        addLog("Trip finished, bike returned: ID=" + bike.getBikeId());
        
        // 计算车费（多态调用）
        if (userEmail != null) {
            RegisteredUsers user = userService.getUserByEmail(userEmail);
            if (user != null) {
                rentalService.endRental(bike.getBikeId(), user);
            } else {
                rentalService.endRental(bike.getBikeId());
            }
        } else {
            rentalService.endRental(bike.getBikeId());
        }

        // Auto process next request in queue
        if (!bikeRequestQueue.isEmpty()) {
            BikeRequest nextRequest = bikeRequestQueue.poll();
            System.out.println("Auto-assigned bike to queued user: " + nextRequest.getUserEmail());
            reserveBike(nextRequest.getRequestLocation(), nextRequest.getUserEmail(), rentalService, userService);
        }
    }
    
    // 辅助方法：根据 bikeID 获取正在租用该车的用户邮箱
    private String getRentalUserEmail(String bikeID, RentalService rentalService) {
        for (ActiveRental rental : rentalService.getActiveRentals()) {
            if (rental.getBikeID().equals(bikeID)) {
                return rental.getUserEmail();
            }
        }
        return null;
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
    
    // 新增：查看可用自行车
    public void viewAvailableBikes() {
        System.out.println("\n===== AVAILABLE BIKES =====");
        boolean found = false;
        for (Bike bike : bikes) {
            if (bike.isAvailable()) {
                System.out.println("Bike ID: " + bike.getBikeId() + " | Location: " + bike.getLocation());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No bikes available at this time.");
        }
    }
    
    // 新增：检查自行车是否可用
    public boolean isBikeAvailable(String bikeID) {
        for (Bike bike : bikes) {
            if (bike.getBikeId().equals(bikeID) && bike.isAvailable()) {
                return true;
            }
        }
        return false;
    }
    
    // 新增：将自行车标记为已租出
    public void markBikeAsRented(String bikeID) {
        for (Bike bike : bikes) {
            if (bike.getBikeId().equals(bikeID)) {
                bike.setAvailable(false);
                return;
            }
        }
    }
    
    // 新增：将自行车标记为可用
    public void markBikeAsAvailable(String bikeID) {
        for (Bike bike : bikes) {
            if (bike.getBikeId().equals(bikeID)) {
                bike.setAvailable(true);
                return;
            }
        }
    }

    // Getters
    public Queue<BikeRequest> getBikeRequestQueue() {
        return bikeRequestQueue;
    }
}