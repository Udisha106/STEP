package Practice;

class BusTicketAccount {

    protected String bookingId;
    protected double ticketFare;

    static int totalProcessed;

    static {
        totalProcessed = 0;
    }

    public BusTicketAccount(String bookingId, double ticketFare) {

        if (ticketFare < 0) {
            throw new IllegalArgumentException("Invalid fare");
        }

        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    public BusTicketAccount(String bookingId) {
        this(bookingId, 0.0);
    }

    public final double calculatePenalty(int minutesLate) {

        if (minutesLate < 0) {
            throw new IllegalArgumentException("Invalid minutes");
        }

        if (minutesLate == 0) {
            return 0.0;
        }

        double penalty = 0.0;

        int first = Math.min(minutesLate, 5);
        penalty += first * ticketFare * 0.005;

        if (minutesLate > 5) {
            int second = Math.min(minutesLate, 15) - 5;
            penalty += second * ticketFare * 0.01;
        }

        if (minutesLate > 15) {
            int third = minutesLate - 15;
            penalty += third * ticketFare * 0.02;
        }

        return penalty;
    }

    public void processAccount(
            BusTicketAccount account,
            double amount,
            int minutesLate) {

        double penalty =
                account.calculatePenalty(minutesLate);

        System.out.println(
                "Booking: " + account.bookingId +
                        " | Amount: " + amount +
                        " | Penalty: " + penalty
        );
    }
}


class Sleeper extends BusTicketAccount {

    public Sleeper(String bookingId, double ticketFare) {
        super(bookingId, ticketFare);
    }

    public Sleeper(String bookingId) {
        super(bookingId);
    }
}
