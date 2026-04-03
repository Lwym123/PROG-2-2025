public class RegisteredUsers {
    private final String email;
    private final String fullName;

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

    @Override
    public String toString() {
        return "User: " + email + " | Name: " + fullName;
    }
}