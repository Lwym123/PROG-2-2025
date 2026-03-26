import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class AdminPanel {
    ArrayList<RegisteredUsers> registeredUsersList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    
    public void userManagementOptions() {
        while (true) {
            System.out.println("\n1. Add New Users");
            System.out.println("2. View Registered Users");
            System.out.println("3. Remove Registered Users");
            System.out.println("4. Update Registered Users");
            System.out.println("5. EXIT");
            System.out.print("Choose: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == 1) {
                addNewUsers();
            } else if (choice == 2) {
                viewRegisteredUsers();
            } else if (choice == 3) {
                removeRegisteredUsers();
            } else if (choice == 4) {
                updateRegisteredUsers();
            } else if (choice == 5) {
                System.out.println("Goodbye!");
                System.exit(0);
            } else {
                System.out.println("Invalid choice. Please try again");
            }
        }
    }
    
    private void addNewUsers() {
        System.out.print("How many users to add? ");
        int num = scanner.nextInt();
        scanner.nextLine();
        
        for (int i = 0; i < num; i++) {
            System.out.println("\n--- User " + (i + 1) + " ---");
            
            System.out.print("Full name: ");
            String name = scanner.nextLine();
            
            System.out.print("Email: ");
            String email = scanner.nextLine();
            
            System.out.print("Date of birth: ");
            String dob = scanner.nextLine();
            
            System.out.print("Card number: ");
            String cardNum = scanner.nextLine();
            
            System.out.print("Card provider: ");
            String cardProv = scanner.nextLine();
            
            System.out.print("Card expiry date: ");
            String expiry = scanner.nextLine();
            
            System.out.print("CVV: ");
            String cvv = scanner.nextLine();
            
            System.out.print("User type: ");
            String userType = scanner.nextLine();
            
            String[] trips = new String[3];
            for (int j = 0; j < 3; j++) {
                System.out.println("\nTrip " + (j + 1) + ":");
                System.out.print("Date (YYYY-MM-DD): ");
                String date = scanner.nextLine();
                
                System.out.print("Source: ");
                String source = scanner.nextLine();
                
                System.out.print("Destination: ");
                String dest = scanner.nextLine();
                
                System.out.print("Fare (€): ");
                String fare = scanner.nextLine();
                
                System.out.print("Feedback: ");
                String feedback = scanner.nextLine();
                
                StringBuilder sb = new StringBuilder();
                sb.append("Date: ").append(date);
                sb.append(", Source: ").append(source);
                sb.append(", Destination: ").append(dest);
                sb.append(", Fare (€): ").append(fare);
                sb.append(", Feedback: ").append(feedback);
                
                trips[j] = sb.toString();
            }
            
            RegisteredUsers user = new RegisteredUsers(name, email, dob, cardNum, 
                                                      expiry, cardProv, cvv, userType, trips);
            registeredUsersList.add(user);
        }
        
        System.out.println("\nAdded " + num + " users!");
    }
    
    private void viewRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to display");
            return;
        }
        
        System.out.println("\n=== All Users ===");
        for (int i = 0; i < registeredUsersList.size(); i++) {
            System.out.println(registeredUsersList.get(i).toString());
            System.out.println("-------------------");
        }
    }
    
    private void removeRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to remove");
            return;
        }
        
        System.out.print("Enter email to remove: ");
        String email = scanner.nextLine();
        
        Iterator<RegisteredUsers> it = registeredUsersList.iterator();
        boolean found = false;
        
        while (it.hasNext()) {
            RegisteredUsers user = it.next();
            if (user.getEmailAddress().equals(email)) {
                it.remove();
                found = true;
                System.out.println("User removed!");
                break;
            }
        }
        
        if (!found) {
            System.out.println("No user found with this email address");
        }
    }
    
    private void updateRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to remove");
            return;
        }
        
        System.out.print("Enter email to update: ");
        String email = scanner.nextLine();
        
        RegisteredUsers user = null;
        for (int i = 0; i < registeredUsersList.size(); i++) {
            if (registeredUsersList.get(i).getEmailAddress().equals(email)) {
                user = registeredUsersList.get(i);
                break;
            }
        }
        
        if (user == null) {
            System.out.println("No user found with this email address");
            return;
        }
        
        System.out.print("New full name (ENTER for no change): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            user.setFullName(newName);
        }
        
        System.out.print("New email (ENTER for no change): ");
        String newEmail = scanner.nextLine();
        if (!newEmail.isEmpty()) {
            user.setEmailAddress(newEmail);
        }
        
        System.out.print("New date of birth (ENTER for no change): ");
        String newDob = scanner.nextLine();
        if (!newDob.isEmpty()) {
            user.setDateOfBirth(newDob);
        }
        
        System.out.print("New card number (0 for no change): ");
        String newCard = scanner.nextLine();
        if (!newCard.equals("0")) {
            user.setCardNumber(newCard);
        }
        
        System.out.print("New card expiry (ENTER for no change): ");
        String newExpiry = scanner.nextLine();
        if (!newExpiry.isEmpty()) {
            user.setCardExpiryDate(newExpiry);
        }
        
        System.out.print("New card provider (ENTER for no change): ");
        String newProvider = scanner.nextLine();
        if (!newProvider.isEmpty()) {
            user.setCardProvider(newProvider);
        }
        
        System.out.print("New CVV (0 for no change): ");
        String newCvv = scanner.nextLine();
        if (!newCvv.equals("0")) {
            user.setCvv(newCvv);
        }
        
        System.out.print("New user type (ENTER for no change): ");
        String newType = scanner.nextLine();
        if (!newType.isEmpty()) {
            user.setUserType(newType);
        }
        
        System.out.println("User updated!");
    }
}