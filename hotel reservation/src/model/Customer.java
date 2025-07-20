package model;
import java.util.regex.Pattern;

public record Customer(String email, String firstName, String lastName) {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public Customer(String email, String firstName, String lastName) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email: " + email);
        }
        this.email = email.toLowerCase();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    @Override
    public String toString() {
        return "Customer Name: " + firstName() + " " + lastName() + ", Email: " + email();
    }

}
