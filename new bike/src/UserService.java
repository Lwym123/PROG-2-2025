import java.util.ArrayList;
import java.util.Scanner;

public class UserService {
    private ArrayList<RegisteredUsers> registeredUsers;
    
    public UserService() {
        registeredUsers = new ArrayList<>();
        // 添加初始用户（作为普通用户）
        registeredUsers.add(new RegularUser("john@example.com", "John Doe"));
        registeredUsers.add(new RegularUser("jane@example.com", "Jane Doe"));
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
     * Add a user（原来版本，保留兼容）
     */
    public void addUser(String email, String name) {
        addUserWithType(email, name, "Regular");
    }
    
    /**
     * 根据用户类型添加用户（符合习题要求）
     * 返回创建的用户对象
     */
    public RegisteredUsers addUserWithType(String email, String name, String userType) {
        if (isRegisteredUser(email)) {
            System.out.println("User with email " + email + " already exists.");
            return null;
        }
        
        RegisteredUsers newUser;
        
        // 根据用户类型创建不同的子类对象（多态）
        if (userType.equalsIgnoreCase("VIP")) {
            newUser = new VIPUser(email, name);
            System.out.println("VIP User " + name + " (" + email + ") has been registered.");
        } else {
            newUser = new RegularUser(email, name);
            System.out.println("Regular User " + name + " (" + email + ") has been registered.");
        }
        
        registeredUsers.add(newUser);
        return newUser;
    }
    
    /**
     * 交互式注册新用户（支持选择用户类型）
     */
    public void registerNewUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter email address: ");
        String email = scanner.nextLine();
        System.out.print("Enter full name: ");
        String name = scanner.nextLine();
        System.out.print("Enter user type (Regular/VIP): ");
        String userType = scanner.nextLine();
        
        addUserWithType(email, name, userType);
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
     * View all users（增强版，显示用户类型）
     */
    public void viewAllUsers() {
        if (registeredUsers.isEmpty()) {
            System.out.println("No registered users.");
        } else {
            System.out.println("=== Registered Users ===");
            for (RegisteredUsers user : registeredUsers) {
                System.out.print(user);
                System.out.print(" | Type: ");
                user.displayUserType();
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
     * 根据邮箱获取用户对象（供 RentalService 使用）
     */
    public RegisteredUsers getUserByEmail(String email) {
        for (RegisteredUsers user : registeredUsers) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
    
    public ArrayList<RegisteredUsers> getRegisteredUsers() {
        return registeredUsers;
    }
}
