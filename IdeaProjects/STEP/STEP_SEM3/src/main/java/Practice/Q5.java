package Practice;


import java.util.*;

public class Q5 {
    interface PaymentMethod {
        boolean pay(double amount);
        String getName();
    }

    static class CreditCardPayment implements PaymentMethod {
        public boolean pay(double amount) {
            return true;
        }

        public String getName() {
            return "Credit Card";
        }
    }

    static class DigitalWalletPayment implements PaymentMethod {
        public boolean pay(double amount) {
            return false;
        }

        public String getName() {
            return "Digital Wallet";
        }
    }

    static class FoodItem {
        String name;
        double price;

        FoodItem(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    static class LineItem {
        FoodItem item;
        int quantity;

        LineItem(FoodItem item, int quantity) {
            this.item = item;
            this.quantity = quantity;
        }

        double subtotal() {
            return item.price * quantity;
        }
    }

    static class Customer {
        String name;
        List<Order> orders = new ArrayList<>();

        Customer(String name) {
            this.name = name;
        }
    }

    static class Notifier {
        void notifyCustomer(String message) {
            System.out.println("Notification: " + message);
        }
    }

    static class Order {
        int id;
        Customer customer;
        List<LineItem> items = new ArrayList<>();
        String status = "Created";
        Notifier notifier = new Notifier();

        Order(int id, Customer customer) {
            this.id = id;
            this.customer = customer;
            customer.orders.add(this);
            System.out.println("Order created.");
        }

        void addItem(FoodItem item, int quantity) {
            if (!"Created".equals(status) || quantity <= 0) {
                System.out.println("Cannot add item.");
                return;
            }

            items.add(new LineItem(item, quantity));
            System.out.println("Added " + item.name
                    + " (Qty " + quantity + ").");
        }

        double total() {
            double amount = 0;
            for (LineItem item : items) {
                amount += item.subtotal();
            }
            return amount;
        }

        void place(PaymentMethod method) {
            if (!"Created".equals(status)) {
                System.out.println("Order cannot be placed again.");
                return;
            }

            if (items.isEmpty()) {
                System.out.println(
                        "Cannot place order: Order must contain at least one item.");
                return;
            }

            status = "Pending Payment";
            System.out.println("Order placed.");

            if (method.pay(total())) {
                status = "Paid";
                System.out.println("Payment via " + method.getName()
                        + " successful.");
                System.out.println("Order status: " + status);
                notifier.notifyCustomer(
                        "Order #" + id + " placed and paid.");
            } else {
                System.out.println("Payment via " + method.getName()
                        + " failed.");
                System.out.println("Order status: " + status);
                notifier.notifyCustomer(
                        "Order #" + id + " placed, awaiting payment.");
            }
        }
    }

    public static void main(String[] args) {
        Customer c = new Customer("John");

        Order o1 = new Order(123, c);
        o1.place(new CreditCardPayment());

        o1.addItem(new FoodItem("Pizza", 200), 2);
        o1.addItem(new FoodItem("Soda", 50), 1);
        o1.place(new CreditCardPayment());

        Order o2 = new Order(124, c);
        o2.addItem(new FoodItem("Burger", 150), 1);
        o2.place(new DigitalWalletPayment());
    }
}

