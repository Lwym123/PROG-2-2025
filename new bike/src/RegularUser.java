public class RegularUser extends RegisteredUsers {

    public RegularUser(String email, String fullName) {
        super(email, fullName);
    }

    @Override
    public double calculateFare(double baseFare) {
        return super.calculateFare(baseFare);
    }

    @Override
    public void displayUserType() {
        super.displayUserType();
    }
}