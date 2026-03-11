public class ERyder {
    
    // Exercise 4 的常量
    public static final String COMPANY_NAME = "ERyder";
    public static final double BASE_FARE = 1.0;
    public static final double PER_MINUTE_FARE = 0.5;
    
    // Exercise 4 的final变量
    private final String LINKED_ACCOUNT;
    private final String LINKED_PHONE_NUMBER;
    
    // Exercise 3 的变量
    private String bikeID;
    private int batteryLevel;
    private boolean isAvailable;
    private double kmDriven;
    
    // Exercise 4 新加的变量
    private int totalUsageInMinutes;
    private double totalFare;
    
    // 构造函数1：有账号和电话
    public ERyder(String bikeID, int batteryLevel, boolean isAvailable, 
                  double kmDriven, String linkedAccount, String linkedPhoneNumber) {
        this.bikeID = bikeID;
        this.batteryLevel = batteryLevel;
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
        this.LINKED_ACCOUNT = linkedAccount;
        this.LINKED_PHONE_NUMBER = linkedPhoneNumber;
        this.totalUsageInMinutes = 0;
        this.totalFare = 0.0;
    }
    
    // 构造函数2：只有单车信息，没有账号
    public ERyder(String bikeID, int batteryLevel, boolean isAvailable, double kmDriven) {
        this.bikeID = bikeID;
        this.batteryLevel = batteryLevel;
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
        this.LINKED_ACCOUNT = "guest";
        this.LINKED_PHONE_NUMBER = "000-0000";
        this.totalUsageInMinutes = 0;
        this.totalFare = 0.0;
    }
    
    // Exercise 3的ride方法
    public void ride() {
        if (isAvailable && batteryLevel > 0) {
            System.out.println("Bike " + bikeID + " is available");
        } else {
            System.out.println("Bike " + bikeID + " is not available");
        }
    }
    
    // Exercise 3的打印方法
    public void printBikeDetails() {
        System.out.println("Bike ID: " + bikeID);
        System.out.println("Battery: " + batteryLevel + "%");
        System.out.println("Available: " + isAvailable);
        System.out.println("Distance: " + kmDriven + " km");
    }
    
    // Exercise 4的新方法：打印骑行详情
    public void printRideDetails(int usageInMinutes) {
        double fare = calculateFare(usageInMinutes);
        
        // 更新累计数据
        totalUsageInMinutes = totalUsageInMinutes + usageInMinutes;
        totalFare = totalFare + fare;
        
        System.out.println("Account: " + LINKED_ACCOUNT);
        System.out.println("Phone: " + LINKED_PHONE_NUMBER);
        System.out.println("Bike ID: " + bikeID);
        System.out.println("Minutes: " + usageInMinutes);
        System.out.println("Fare: $" + fare);
    }
    
    // Exercise 4的私有方法
    private double calculateFare(int usageInMinutes) {
        return BASE_FARE + (PER_MINUTE_FARE * usageInMinutes);
    }
    
    // Getter和Setter
    public String getBikeID() {
        return bikeID;
    }
    
    public void setBikeID(String bikeID) {
        this.bikeID = bikeID;
    }
    
    public int getBatteryLevel() {
        return batteryLevel;
    }
    
    // 设置电量时检查范围
    public void setBatteryLevel(int batteryLevel) {
        if (batteryLevel >= 0 && batteryLevel <= 100) {
            this.batteryLevel = batteryLevel;
        } else {
            System.out.println("Error: Battery level must be between 0 and 100");
        }
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    
    public double getKmDriven() {
        return kmDriven;
    }
    
    public void setKmDriven(double kmDriven) {
        if (kmDriven >= 0) {
            this.kmDriven = kmDriven;
        } else {
            System.out.println("Error: Distance cannot be negative");
        }
    }
    
    public String getLinkedAccount() {
        return LINKED_ACCOUNT;
    }
    
    public String getLinkedPhoneNumber() {
        return LINKED_PHONE_NUMBER;
    }
    
    public int getTotalUsageInMinutes() {
        return totalUsageInMinutes;
    }
    
    public double getTotalFare() {
        return totalFare;
    }
}