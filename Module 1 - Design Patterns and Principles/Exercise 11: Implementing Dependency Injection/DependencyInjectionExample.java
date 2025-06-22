public class DependencyInjectionExample {
    public static void main(String[] args) {
        CustomerRepository repository = new CustomerRepositoryImpl();
        CustomerService service = new CustomerService(repository);

        Customer customer = service.findCustomerById("6421155");
        System.out.println("Customer found: " + customer);
    }
}

interface CustomerRepository {
    Customer findCustomerById(String id);
}

class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public Customer findCustomerById(String id) {
        // Simulate fetching from a database
        return new Customer(id, "Mohideen");
    }
}

class CustomerService {
    private final CustomerRepository repository;

    // Constructor injection
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer findCustomerById(String id) {
        return repository.findCustomerById(id);
    }
}

class Customer {
    private String id;
    private String name;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer{id='" + id + "', name='" + name + "'}";
    }
}
