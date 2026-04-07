public class RegisteredUsers {
    private String email;
    private String fullName;

    public RegisteredUsers(String email, String fullName) {
        this.email = email;
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setName(String newName) {
        this.fullName = newName;
    }

    /**
     * 计算车费（基类：原价）
     */
    public double calculateFare(double baseFare) {
        return baseFare;
    }

    /**
     * 显示用户类型
     */
    public void displayUserType() {
        System.out.println("Regular User");
    }

    @Override
    public String toString() {
        return "User: " + email + " | Name: " + fullName;
    }
}