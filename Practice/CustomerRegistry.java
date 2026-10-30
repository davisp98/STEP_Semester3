import java.util.*;

class Customer {
    private int id;
    private String name;

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

public class CustomerRegistry {
    public static void main(String[] args) {
        Set<Customer> registry = new HashSet<>();

        Customer c1 = new Customer(101, "Asha");
        boolean added1 = registry.add(c1);
        System.out.println(added1 ? "true" : "duplicate rejected");

        Customer c2 = new Customer(101, "Asha");
        boolean added2 = registry.add(c2);
        System.out.println(added2 ? "true" : "false (duplicate rejected)");

        Customer c3 = new Customer(102, "Ravi");
        registry.add(c3);

        Customer query = new Customer(101, "Asha");
        System.out.println("contains (" + query.getId() + ", " + query.getName() + ")?");
        System.out.println("unique count " + registry.size());
        System.out.println("contains: " + registry.contains(query));
    }
}