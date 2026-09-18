package Practice;

public class EventTicket {

    private String attendeeId;
    protected double basePrice;
    private double amountPaid;

    private double[] lateFeeHistory;
    private int lateFeeCount;

    private static int ticketsIssued = 0;
    public final String ticketId;

    public EventTicket(String attendeeId, double basePrice) {

        String id = attendeeId.trim();

        if (id.length() < 4) {
            throw new IllegalArgumentException("Invalid attendee ID");
        }

        this.attendeeId = id;
        this.basePrice = basePrice;
        this.amountPaid = 0;

        this.lateFeeHistory = new double[10];
        this.lateFeeCount = 0;

        ticketsIssued++;
        ticketId = "TCK-" + (1000 + ticketsIssued);
    }

    public EventTicket(double basePrice) {

        this.attendeeId = null;
        this.basePrice = basePrice;
        this.amountPaid = 0;

        this.lateFeeHistory = new double[10];
        this.lateFeeCount = 0;

        ticketsIssued++;
        ticketId = "TCK-" + (1000 + ticketsIssued);
    }

    public void pay(double amount) {
        amountPaid = amountPaid + amount;
    }

    public void pay(double amount, String mode) {

        System.out.println("Payment mode: " + mode);

        pay(amount);
    }

    public double getBalanceDue() {
        return basePrice - amountPaid;
    }

    protected void applyLateFee(double amount) {

        basePrice = basePrice + amount;

        if (lateFeeCount < 10) {
            lateFeeHistory[lateFeeCount] = amount;
            lateFeeCount++;
        }
    }

    public double[] getLateFeeHistory() {

        double[] copy = new double[lateFeeCount];

        for (int i = 0; i < lateFeeCount; i++) {
            copy[i] = lateFeeHistory[i];
        }

        return copy;
    }

    public void printTicket() {

        System.out.print(
                "Standard Event Ticket | Balance Due: "
                        + getBalanceDue()
        );
    }

    public static String registerBatch(
            String[] attendeeIds,
            double basePrice) {

        int registered = 0;
        int rejected = 0;

        for (int i = 0; i < attendeeIds.length; i++) {

            try {

                EventTicket ticket =
                        new EventTicket(attendeeIds[i], basePrice);

                registered++;

            } catch (IllegalArgumentException e) {

                rejected++;
            }
        }

        return "Registered: " + registered
                + " | Rejected: " + rejected;
    }

    public static boolean isValidPromoCode(String code) {

        if (code == null || code.length() != 5) {
            return false;
        }

        if (code.charAt(0) != 'F') {
            return false;
        }

        if (!Character.isDigit(code.charAt(1))) {
            return false;
        }

        if (!Character.isDigit(code.charAt(2))) {
            return false;
        }

        if (!Character.isDigit(code.charAt(3))) {
            return false;
        }

        if (!Character.isUpperCase(code.charAt(4))) {
            return false;
        }

        return true;
    }

    public static int getTicketsIssued() {
        return ticketsIssued;
    }

    public static double getTotalBalanceDue(
            EventTicket[] tickets) {

        double total = 0;

        for (int i = 0; i < tickets.length; i++) {
            total = total + tickets[i].getBalanceDue();
        }

        return total;
    }

    public static String classifyGeneration(EventTicket ticket) {

        if (ticket instanceof PremiumWorkshopTicket) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (ticket instanceof HackathonTicket) {
            return "Hierarchical sibling (independent branch)";
        }

        if (ticket instanceof WorkshopTicket) {
            return "Direct subclass";
        }

        return "Base Event Ticket";
    }

    public static String batchPrint(EventTicket[] tickets) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < tickets.length; i++) {

            EventTicket ticket = tickets[i];

            ticket.printTicket();

            if (ticket instanceof WorkshopTicket) {

                WorkshopTicket workshop =
                        (WorkshopTicket) ticket;

                result.append(
                        " [Track via downcast: "
                                + workshop.getTrack()
                                + "]"
                );
            }

            result.append(" | ");
        }

        return result.toString();
    }

    public static String processNightlySettlement(
            EventTicket[] tickets) {

        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        for (int i = 0; i < tickets.length; i++) {

            if (tickets[i] == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (tickets[i] instanceof GroupTicket) {
                group++;
            } else {
                individual++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + group + " group | "
                + individual + " individual";
    }
}
