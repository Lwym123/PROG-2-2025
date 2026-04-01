import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class ParenthesesBalancer {
    
    /**
     * Check if the parentheses string is balanced and properly paired
     * @param input The string containing parentheses
     * @return true if balanced, false otherwise
     */
    public static boolean isBalanced(String input) {
        // Create a stack using Deque interface and ArrayDeque class
        Deque<Character> stack = new ArrayDeque<>();
        
        // Convert string to character array
        char[] characters = input.toCharArray();
        
        // Loop through each character
        for (char ch : characters) {
            // If character is an opening bracket, push it onto the stack
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            }
            // If character is a closing bracket
            else if (ch == ')' || ch == ']' || ch == '}') {
                // Check if stack is empty - means no matching opening bracket
                if (stack.isEmpty()) {
                    return false;
                }
                
                // Pop the top element from stack
                char top = stack.pop();
                
                // Check if the popped bracket matches the current closing bracket
                if (!isMatchingPair(top, ch)) {
                    return false;
                }
            }
            // Ignore any other characters (optional - can also validate)
            // If you want to only accept parentheses, uncomment the else block:
            // else {
            //     return false; // Invalid character
            // }
        }
        
        // Parentheses are balanced only if the stack is empty at the end
        return stack.isEmpty();
    }
    
    /**
     * Check if opening and closing brackets match
     * @param open The opening bracket
     * @param close The closing bracket
     * @return true if they form a valid pair, false otherwise
     */
    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '[' && close == ']') ||
               (open == '{' && close == '}');
    }
    
    /**
     * Main method to run the program
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Parentheses Balance Checker ===");
        System.out.println("This program checks if parentheses are balanced and properly paired.");
        System.out.println("Supported parentheses: (), [], {}");
        System.out.println("Enter 'quit' to exit.\n");
        
        while (true) {
            System.out.print("Enter a parentheses string: ");
            String input = scanner.nextLine();
            
            // Exit condition
            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Exiting program...");
                break;
            }
            
            // Check if the string is balanced
            boolean balanced = isBalanced(input);
            
            // Display result
            if (balanced) {
                System.out.println("Result: BALANCED ✓");
                System.out.println("The parentheses are properly paired and balanced.\n");
            } else {
                System.out.println("Result: NOT BALANCED ✗");
                System.out.println("The parentheses are not properly paired or balanced.\n");
            }
        }
        
        scanner.close();
    }
}