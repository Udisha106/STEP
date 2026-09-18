package Practice;

class FareSplitter {
    private String tripId;
    private double totalFare;
    private int passengerCount;

    public FareSplitter(String tripId, double totalFare,
                        int passengerCount) {

        if (totalFare < 0 || passengerCount <= 0) {
            throw new IllegalArgumentException("Invalid fare or count");
        }

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 1);
    }

    public FareSplitter(String tripId) {
        this(tripId, 0.0, 2);
    }

    public double[] fareBreakdown() {

        long totalPaise = Math.round(totalFare * 100);

        long each = totalPaise / passengerCount;
        long remainder = totalPaise % passengerCount;

        double[] result = new double[passengerCount];

        for (int i = 0; i < passengerCount; i++) {
            long share = each;

            if (i == passengerCount - 1) {
                share += remainder;
            }

            result[i] = share / 100.0;
        }

        return result;
    }

    public boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }
}
