public class ERyder {
    // 变量
    private int bikeID;           // 自行车唯一ID
    private int batteryLevel;      // 电池电量百分比
    private boolean isAvailable;   // 自行车是否可用
    private double kmDriven;       // 自行车行驶总里程（公里）
    
    // 默认构造函数
    public ERyder() {
        this.bikeID = 0;
        this.batteryLevel = 0;
        this.isAvailable = false;
        this.kmDriven = 0.0;
    }
    
    // 带所有参数的构造函数
    public ERyder(int bikeID, int batteryLevel, boolean isAvailable, double kmDriven) {
        this.bikeID = bikeID;
        setBatteryLevel(batteryLevel); // 使用setter进行验证
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
    }
    
    // ride()方法 - 检查电池电量和可用性
    public void ride() {
        if (isAvailable && batteryLevel > 0) {
            System.out.println("自行车 " + bikeID + " 可以骑行。");
        } else {
            System.out.println("自行车 " + bikeID + " 无法骑行。");
            if (!isAvailable) {
                System.out.println("原因：自行车当前不可用。");
            }
            if (batteryLevel <= 0) {
                System.out.println("原因：电池电量过低（" + batteryLevel + "%）。");
            }
        }
    }
    
    // printBikeDetails()方法 - 打印自行车详细信息
    public void printBikeDetails() {
        System.out.println("\n=== 自行车详细信息 ===");
        System.out.println("自行车ID: " + bikeID);
        System.out.println("电池电量: " + batteryLevel + "%");
        System.out.println("可用状态: " + (isAvailable ? "可用" : "不可用"));
        System.out.println("已行驶里程: " + kmDriven + " 公里");
    }
    
    // 电池电量的setter（带验证）
    public void setBatteryLevel(int batteryLevel) {
        if (batteryLevel >= 0 && batteryLevel <= 100) {
            this.batteryLevel = batteryLevel;
        } else {
            System.out.println("错误：电池电量必须在0到100之间。已设置为默认值0。");
            this.batteryLevel = 0;
        }
    }
    
    // Getters
    public int getBikeID() {
        return bikeID;
    }
    
    public int getBatteryLevel() {
        return batteryLevel;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public double getKmDriven() {
        return kmDriven;
    }
    
    // Setters（其他变量）
    public void setBikeID(int bikeID) {
        this.bikeID = bikeID;
    }
    
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    
    public void setKmDriven(double kmDriven) {
        this.kmDriven = kmDriven;
    }
}