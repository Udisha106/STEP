package Assign;

final class SurgeFeeCalculator {

    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(
            double orderValue, int delayMinutes) {

        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        // No fee when there is no delay
        if (delayMinutes == 0) {
            return 0.0;
        }

        double fee = 0.0;

        // Minutes 1-5 → 0.5%
        int firstTier = Math.min(delayMinutes, 5);

        fee += firstTier * orderValue * 0.005;

        // Minutes 6-15 → 1%
        if (delayMinutes > 5) {

            int secondTier =
                    Math.min(delayMinutes, 15) - 5;

            fee += secondTier * orderValue * 0.01;
        }

        // Minute 16 onwards → 2%
        if (delayMinutes > 15) {

            int thirdTier =
                    delayMinutes - 15;

            fee += thirdTier * orderValue * 0.02;
        }

        // Minimum surge floor
        double minimumFee =
                orderValue * minimumSurgePercent / 100.0;

        return Math.max(fee, minimumFee);
    }
}
