package Practice;


import java.util.HashSet;
import java.util.Objects;

class Customer {
    int id;
    String name;

    Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Customer)) {
            return false;
        }

        Customer c = (Customer) obj;
        return id == c.id && name.equals(c.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

public class Q2 {
    public static void main(String[] args) {
        HashSet<Customer> customers = new HashSet<>();

        Customer c1 = new Customer(101, "Asha");
        Customer c2 = new Customer(101, "Asha");
        Customer c3 = new Customer(102, "Ravi");

        System.out.println(customers.add(c1));
        System.out.println(customers.add(c2));
        System.out.println(customers.add(c3));

        System.out.println("Unique count: " + customers.size());
        System.out.println("Contains: " + customers.contains(c1));
    }
}
