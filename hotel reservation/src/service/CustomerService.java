package service;
import model.Customer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    private static final CustomerService instance = new CustomerService(); // Singleton Instance
    private final Map<String, Customer> customers = new HashMap<>(); // Store customers by email

    private CustomerService() {}

    public static CustomerService getInstance() {
        return instance;
    }

    public void addCustomer(String email, String firstName, String lastName) {
        customers.put(email, new Customer(email, firstName, lastName));
    }


    public Customer getCustomer(String email) {
        return customers.get(email.toLowerCase());
    }

    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }
}
