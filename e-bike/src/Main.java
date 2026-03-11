public class Main {
    public static void main(String[] args) {
        System.out.println("Company: " + ERyder.COMPANY_NAME);
        System.out.println("Base Fare: $" + ERyder.BASE_FARE);
        System.out.println("Per Minute: $" + ERyder.PER_MINUTE_FARE);
        
        // 创建第一个对象 - 用第二个构造函数，只有单车参数
        System.out.println("\n===== Creating first bike =====");
        ERyder bike1 = new ERyder("B001", 85, true, 120.3);
        System.out.println("First bike created");
        
        System.out.println("\nCalling printBikeDetails():");
        bike1.printBikeDetails();
        
        // 创建第二个对象 - 用第一个构造函数，有账号电话
        System.out.println("\n===== Creating second bike =====");
        ERyder bike2 = new ERyder("B002", 60, true, 200.5, 
                                  "zhangsan", "13812345678");
        System.out.println("Second bike created");
        
        System.out.println("\nCalling ride():");
        bike2.ride();
        
        System.out.println("\nCalling printBikeDetails():");
        bike2.printBikeDetails();
        
        // 测试printRideDetails
        System.out.println("\n===== Testing printRideDetails =====");
        System.out.println("First bike riding 30 minutes:");
        bike1.printRideDetails(30);
        
        System.out.println("\nSecond bike riding 45 minutes:");
        bike2.printRideDetails(45);
        
        System.out.println("\nFirst bike riding another 20 minutes:");
        bike1.printRideDetails(20);
        
        // setter的错误提示
        System.out.println("\n===== Testing setter error messages =====");
        System.out.println("Trying to set battery to 150:");
        bike1.setBatteryLevel(150);
        System.out.println("Current battery: " + bike1.getBatteryLevel() + "%");
        
        System.out.println("\nTrying to set distance to -10:");
        bike1.setKmDriven(-10);
        System.out.println("Current distance: " + bike1.getKmDriven() + " km");
        
        // 测试私有方法
        System.out.println("\n===== Testing private method =====");
        System.out.println("Q: Can we call calculateFare() directly?");
        System.out.println("A: No, because it's private. Compiler error.");
        System.out.println("Example: bike1.calculateFare(10) // This would cause error");
        
        System.out.println("\nQ: How to use this method?");
        System.out.println("A: It's called inside printRideDetails()");
        
        // 显示最终结果
        System.out.println("\n===== Final Results =====");
        System.out.println("Bike 1 total minutes: " + bike1.getTotalUsageInMinutes());
        System.out.println("Bike 1 total fare: $" + bike1.getTotalFare());
        System.out.println("Bike 2 total minutes: " + bike2.getTotalUsageInMinutes());
        System.out.println("Bike 2 total fare: $" + bike2.getTotalFare());
        
        System.out.println("\nProgram finished");
    }
}