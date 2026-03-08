public class Main {
    public static void main(String[] args) {
        System.out.println("=== eRyder 电动自行车共享服务 ===\n");
        
        // 使用默认构造函数
        System.out.println("使用默认构造函数创建自行车：");
        ERyder bike1 = new ERyder();
        bike1.printBikeDetails();
        
        System.out.println("\n----------------------------------------\n");
        
        // 使用带参数的构造函数
        System.out.println("使用带参数的构造函数创建自行车：");
        ERyder bike2 = new ERyder(1001, 75, true, 125.5);
        
        System.out.println("\n测试ride()方法：");
        bike2.ride();
        bike2.printBikeDetails();
    }
}