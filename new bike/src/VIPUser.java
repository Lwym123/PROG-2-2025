public class VIPUser extends RegisteredUsers {

    public VIPUser(String email, String fullName) {
        super(email, fullName);
    }

    @Override
    public double calculateFare(double baseFare) {
        // VIP 享受 20% 折扣
        return baseFare * 0.8;
    }

    @Override
    public void displayUserType() {
        System.out.println("VIP User");
    }
}