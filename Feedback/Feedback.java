public class Feedback {
    private String firstName;
    private String lastName;
    private String email;
    private String completeFeedback;
    private String reviewID;
    private boolean longFeedback;
    
    public Feedback(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    
    public void analyseFeedback(boolean isConcatenation, String sent1, String sent2, String sent3, String sent4, String sent5) {
        if (isConcatenation == true) {
            completeFeedback = feedbackUsingConcatenation(sent1, sent2, sent3, sent4, sent5);
            checkFeedbackLength(completeFeedback);
            createReviewID(firstName, lastName, completeFeedback);
        }
        if (isConcatenation == false) {
            completeFeedback = feedbackUsingStringBuilder(sent1, sent2, sent3, sent4, sent5);
            checkFeedbackLength(completeFeedback);
            createReviewID(firstName, lastName, completeFeedback);
        }
    }
    
    private String feedbackUsingConcatenation(String sent1, String sent2, String sent3, String sent4, String sent5) {
        String concatenatedFeedback = sent1 + sent2 + sent3 + sent4 + sent5;
        return concatenatedFeedback;
    }
    
    private String feedbackUsingStringBuilder(String sent1, String sent2, String sent3, String sent4, String sent5) {
        StringBuilder sb = new StringBuilder();
        sb.append(sent1);
        sb.append(sent2);
        sb.append(sent3);
        sb.append(sent4);
        sb.append(sent5);
        return sb.toString();
    }
    
    private void checkFeedbackLength(String completeFeedback) {
        if (completeFeedback.length() > 500) {
            longFeedback = true;
        }
        if (completeFeedback.length() <= 500) {
            longFeedback = false;
        }
    }
    
    private void createReviewID(String firstName, String lastName, String completeFeedback) {
        String a = firstName + lastName;
        String b = a.substring(2, 6);
        String c = b.toUpperCase();
        
        String d = completeFeedback.substring(10, 15);
        String e = d.toLowerCase();
        
        String f = Integer.toString(completeFeedback.length());
        
        String g = Long.toString(System.currentTimeMillis());
        
        String h = c + e + f + "_" + g;
        
        reviewID = h.replace(" ", "");
    }
    
    public String toString() {
        return "first name: " + firstName + "\n" + "last name: " + lastName + "\n" + "email: " + email + "\n" + "complete feedback: " + completeFeedback + "\n" + "long feedback: " + longFeedback + "\n" + "review id: " + reviewID;
    }
    
    public static void main(String[] args) {
        String sent1 = "I was very satisfied with the service. ";
        String sent2 = "The e-Bike is quite comfortable to ride. ";
        String sent3 = "The battery life of the e-Bike is impressive. ";
        String sent4 = "The customer support was helpful and responsive. ";
        String sent5 = "I would recommend this e-Bike to my friends and family.";
        
        Feedback f = new Feedback("John", "Doe", "john.doe@email.com");
        
        f.analyseFeedback(true, sent1, sent2, sent3, sent4, sent5);
        
        System.out.println(f);
    }
}
