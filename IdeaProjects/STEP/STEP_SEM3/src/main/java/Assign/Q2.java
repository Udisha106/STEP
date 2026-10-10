package Assign;


import java.util.*;

public class Q2 {
    enum Status {
        BOOKED, PICKED_UP, IN_TRANSIT,
        OUT_FOR_DELIVERY, DELIVERED, CANCELLED
    }

    interface ShippingType {
        double charge(double weight);
        String name();
    }

    static class Standard implements ShippingType {
        public double charge(double w) { return 40 + 10 * w; }
        public String name() { return "Standard"; }
    }

    static class Express implements ShippingType {
        public double charge(double w) { return 80 + 15 * w; }
        public String name() { return "Express"; }
    }

    static class Fragile implements ShippingType {
        public double charge(double w) {
            return new Standard().charge(w) + 50;
        }
        public String name() { return "Fragile"; }
    }

    interface NotificationChannel {
        void notify(String id, Status status);
    }

    static class Sms implements NotificationChannel {
        public void notify(String id, Status s) {
            System.out.println("[SMS] " + id + " is now " + s + ".");
        }
    }

    static class Email implements NotificationChannel {
        public void notify(String id, Status s) {
            System.out.println("[Email] " + id + " is now " + s + ".");
        }
    }

    static class Customer {
        String name;
        Customer(String name) { this.name = name; }
    }

    static class Parcel {
        String id;
        Customer customer;
        double weight;
        ShippingType type;
        Status status = Status.BOOKED;
        List<NotificationChannel> channels;

        Parcel(String id, Customer c, double w, ShippingType t,
               List<NotificationChannel> channels) {
            this.id = id;
            customer = c;
            weight = w;
            type = t;
            this.channels = channels;
        }

        void notifyChannels() {
            for (NotificationChannel c : channels) {
                c.notify(id, status);
            }
        }

        void update(Status next) {
            Status expected = null;

            switch (status) {
                case BOOKED: expected = Status.PICKED_UP; break;
                case PICKED_UP: expected = Status.IN_TRANSIT; break;
                case IN_TRANSIT: expected = Status.OUT_FOR_DELIVERY; break;
                case OUT_FOR_DELIVERY: expected = Status.DELIVERED; break;
            }

            if (next != expected) {
                System.out.println("Invalid transition: " + status
                        + " → " + next + " is not allowed.");
                return;
            }

            status = next;
            notifyChannels();
        }

        void cancel() {
            if (status != Status.BOOKED) {
                System.out.println("Cancellation failed: " + id
                        + " can be cancelled only while BOOKED.");
                return;
            }

            status = Status.CANCELLED;
            notifyChannels();
        }
    }

    static class ParcelService {
        Parcel book(Customer c, String id, double weight,
                    ShippingType type, List<NotificationChannel> channels) {
            if (weight <= 0) {
                System.out.println("Booking failed: Invalid weight.");
                return null;
            }

            Parcel p = new Parcel(id, c, weight, type, channels);
            System.out.println("Parcel " + id + " booked ("
                    + type.name() + ", " + weight + " kg).");
            System.out.printf(Locale.US, "Charge: ₹%.2f%n",
                    type.charge(weight));
            p.notifyChannels();
            return p;
        }
    }

    public static void main(String[] args) {
        Customer c = new Customer("Asha");
        ParcelService service = new ParcelService();

        List<NotificationChannel> channels =
                Arrays.asList(new Sms(), new Email());

        Parcel p = service.book(c, "P101", 2,
                new Express(), channels);

        if (p != null) {
            p.update(Status.PICKED_UP);
            p.cancel();
            p.update(Status.IN_TRANSIT);
            p.update(Status.DELIVERED);
        }
    }
}

