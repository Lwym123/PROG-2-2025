import java.util.ArrayList;
import java.util.Scanner;

public class UserService {
    private ArrayList<RegisteredUsers> registeredUsers;
    
    public UserService() {
        registeredUsers = new ArrayList<>();
        // Add some initial demo users
        registeredUsers.add(new RegisteredUsers("john@example.com", "John Doe"));
        registeredUsers.add(new RegisteredUsers("jane@example.com", "Jane Doe"));
    }
    
    /**
     * Check if a user is registered
     */
    public boolean isRegisteredUser(String email) {
        for (RegisteredUsers user : registeredUsers) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Add a user
     */
    public void addUser(String email, String name) {
        if (!isRegisteredUser(email)) {
            registeredUsers.add(new RegisteredUsers(email, name));
            System.out.println("User " + name + " (" + email + ") has been registered.");
        } else {
            System.out.println("User with email " + email + " already exists.");
        }
    }
    
    /**
     * Remove a user
     */
    public void removeUser(String email) {
        for (int i = 0; i < registeredUsers.size(); i++) {
            if (registeredUsers.get(i).getEmail().equals(email)) {
                registeredUsers.remove(i);
                System.out.println("User " + email + " has been removed.");
                return;
            }
        }
        System.out.println("User with email " + email + " not found.");
    }
    
    /**
     * Update user information
     */
    public void updateUser(String email, String newName) {
        for (RegisteredUsers user : registeredUsers) {
            if (user.getEmail().equals(email)) {
                user.setName(newName);
                System.out.println("User " + email + " updated to name: " + newName);
                return;
            }
        }
        System.out.println("User with email " + email + " not found.");
    }
    
    /**
     * View all users
     */
    public void viewAllUsers() {
        if (registeredUsers.isEmpty()) {
            System.out.println("No registered users.");
        } else {
            System.out.println("=== Registered Users ===");
            for (RegisteredUsers user : registeredUsers) {
                System.out.println(user);
            }
        }
    }
    
    /**
     * Get user information
     */
    public String getUserInfo(String email) {
        for (RegisteredUsers user : registeredUsers) {
            if (user.getEmail().equals(email)) {
                return user.toString();
            }
        }
        return null;
    }
    
    /**
     * Register a new user (interactive)
     */
    public void registerNewUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter email address: ");
        String email = scanner.nextLine();
        System.out.print("Enter full name: ");
        String name = scanner.nextLine();
        addUser(email, name);
    }
}