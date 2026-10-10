package Practice;


import java.time.*;
import java.util.*;

public class Q3 {
    static class Customer {
        String name;

        Customer(String name) {
            this.name = name;
        }
    }

    static class Room {
        String number;
        String category;
        double rate;

        Room(String number, String category, double rate) {
            this.number = number;
            this.category = category;
            this.rate = rate;
        }
    }

    static class Reservation {
        Room room;
        Customer customer;
        LocalDate checkIn, checkOut, cancelDeadline;
        double price;
        boolean active = true;

        Reservation(Room room, Customer customer,
                    LocalDate in, LocalDate out,
                    LocalDate deadline) {
            this.room = room;
            this.customer = customer;
            this.checkIn = in;
            this.checkOut = out;
            this.cancelDeadline = deadline;
            this.price = room.rate * (out.toEpochDay() - in.toEpochDay());
        }
    }

    static class Hotel {
        List<Room> rooms = new ArrayList<>();
        List<Reservation> reservations = new ArrayList<>();

        Reservation book(Room room, Customer customer,
                         LocalDate in, LocalDate out,
                         LocalDate deadline) {
            if (!out.isAfter(in)) {
                System.out.println("Booking failed: invalid dates.");
                return null;
            }

            for (Reservation r : reservations) {
                boolean overlaps = in.isBefore(r.checkOut)
                        && r.checkIn.isBefore(out);

                if (r.room == room && r.active && overlaps) {
                    System.out.println("Booking failed: " + room.category
                            + " Room " + room.number
                            + " is not available for " + in + " to " + out);
                    return null;
                }
            }

            Reservation r = new Reservation(
                    room, customer, in, out, deadline);
            reservations.add(r);

            System.out.println(room.category + " Room " + room.number
                    + " booked from " + in + " to " + out + ".");

            System.out.printf("Total price: $%.2f%n", r.price);
            return r;
        }

        void cancel(Reservation r, LocalDate today) {
            if (r == null || !r.active) {
                System.out.println("Reservation cannot be cancelled.");
                return;
            }

            if (today.isAfter(r.cancelDeadline)) {
                System.out.println("Cancellation deadline has passed.");
                return;
            }

            r.active = false;
            System.out.println("Reservation for Room "
                    + r.room.number + " cancelled successfully.");
        }
    }

    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        Room deluxe = new Room("101", "Deluxe", 200);
        Room standard = new Room("205", "Standard", 150);

        Customer c1 = new Customer("Alice");
        Customer c2 = new Customer("Bob");

        LocalDate in1 = LocalDate.parse("2024-12-01");
        LocalDate out1 = LocalDate.parse("2024-12-05");

        Reservation r1 = hotel.book(
                deluxe, c1, in1, out1,
                LocalDate.parse("2024-11-28"));

        hotel.book(standard, c2,
                LocalDate.parse("2024-12-03"),
                LocalDate.parse("2024-12-07"),
                LocalDate.parse("2024-11-30"));

        hotel.book(deluxe, c2,
                LocalDate.parse("2024-12-03"),
                LocalDate.parse("2024-12-07"),
                LocalDate.parse("2024-11-30"));

        hotel.cancel(r1, LocalDate.parse("2024-11-27"));
    }
}
