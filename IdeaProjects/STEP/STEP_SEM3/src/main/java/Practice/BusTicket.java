package Practice;

class BusTicket {
    private String passengerName;
    private String destination;
    private boolean checkedIn;

    public BusTicket(String passengerName, String destination) {
        if (passengerName == null || destination == null ||
                passengerName.trim().isEmpty() ||
                destination.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid booking");
        }

        if (!passengerName.matches("[A-Za-z ]+")) {
            throw new IllegalArgumentException("Invalid name");
        }

        this.passengerName = passengerName;
        this.destination = destination;
        checkedIn = false;
    }

    public void markCheckedIn() {
        if (checkedIn) {
            throw new IllegalStateException("Already checked in");
        }
        checkedIn = true;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getDestination() {
        return destination;
    }

    public static void processBatch(String[][] rawBookings) {
        String[] accepted = new String[rawBookings.length];

        int valid = 0;
        int rejected = 0;
        int duplicates = 0;

        for (String[] booking : rawBookings) {
            try {
                BusTicket ticket =
                        new BusTicket(booking[0], booking[1]);

                String key =
                        ticket.getPassengerName().trim().toLowerCase()
                                + "|"
                                + ticket.getDestination().trim().toLowerCase();

                boolean duplicate = false;

                for (int i = 0; i < valid; i++) {
                    if (accepted[i].equals(key)) {
                        duplicate = true;
                        break;
                    }
                }

                if (duplicate) {
                    duplicates++;
                } else {
                    accepted[valid] = key;
                    valid++;
                }

            } catch (Exception e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid +
                " | Rejected: " + rejected +
                " | Duplicates skipped: " + duplicates);
    }
}
