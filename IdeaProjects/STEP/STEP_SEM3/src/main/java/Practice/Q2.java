package Practice;


import java.util.*;

public class Q2 {
    static abstract class Vehicle {
        String name;
        boolean available = true;

        Vehicle(String name) {
            this.name = name;
        }

        abstract double dailyRate();
    }

    static class StandardCar extends Vehicle {
        StandardCar(String name) {
            super(name);
        }

        double dailyRate() {
            return 50;
        }
    }

    static class LuxuryCar extends Vehicle {
        LuxuryCar(String name) {
            super(name);
        }

        double dailyRate() {
            return 100;
        }
    }

    static class Customer {
        String name;

        Customer(String name) {
            this.name = name;
        }
    }

    static class Rental {
        Customer customer;
        Vehicle vehicle;
        int days;
        double amount;
        boolean active = true;

        Rental(Customer customer, Vehicle vehicle, int days) {
            this.customer = customer;
            this.vehicle = vehicle;
            this.days = days;
            this.amount = vehicle.dailyRate() * days;
        }
    }

    static class RentalService {
        List<Rental> rentals = new ArrayList<>();

        Rental rent(Customer c, Vehicle v, int days) {
            if (!v.available || days <= 0) {
                System.out.println("Rental failed: vehicle unavailable "
                        + "or invalid duration.");
                return null;
            }

            Rental r = new Rental(c, v, days);
            rentals.add(r);
            v.available = false;

            System.out.printf("%s rented for %d days. Total charge: $%.2f%n",
                    v.name, days, r.amount);
            return r;
        }

        void returnVehicle(Rental r) {
            if (r == null || !r.active) {
                System.out.println("Invalid or completed rental.");
                return;
            }

            r.active = false;
            r.vehicle.available = true;
            System.out.println(r.vehicle.name + " returned. Now available.");
        }
    }

    public static void main(String[] args) {
        Customer c = new Customer("John");
        Vehicle v1 = new LuxuryCar("Luxury Car A");
        Vehicle v2 = new StandardCar("Standard Car B");

        RentalService service = new RentalService();

        Rental r1 = service.rent(c, v1, 3);
        service.rent(c, v2, 5);
        service.returnVehicle(r1);
    }
}
