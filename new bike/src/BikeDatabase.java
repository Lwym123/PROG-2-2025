import java.util.ArrayList;
import java.util.List;

// Static database for all bikes
public class BikeDatabase {
    public static final List<Bike> bikes = new ArrayList<>();

    static {
        // Initialize sample bikes
        bikes.add(new Bike("B001", "Campus"));
        bikes.add(new Bike("B002", "Downtown"));
        bikes.add(new Bike("B003", "Campus"));
    }
}