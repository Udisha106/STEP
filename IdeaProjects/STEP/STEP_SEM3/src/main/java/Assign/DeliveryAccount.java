package Assign;

class DeliveryAccount {

    protected String studentId;
    protected double orderValue;

    static int totalProcessed;

    // Static block
    static {
        totalProcessed = 0;
    }

    public DeliveryAccount(String studentId, double orderValue) {

        if (orderValue < 0) {
            throw new IllegalArgumentException("Invalid order value");
        }

        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }

    // Final surge-fee calculation
    public final double calculateSurgeFee(int delayMinutes) {

        if (delayMinutes < 0) {
            throw new IllegalArgumentException("Invalid delay");
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        double fee = 0.0;

        // Minutes 1-5 = 0.5%
        int firstTier = Math.min(delayMinutes, 5);

        fee += firstTier * orderValue * 0.005;

        // Minutes 6-15 = 1%
        if (delayMinutes > 5) {

            int secondTier =
                    Math.min(delayMinutes, 15) - 5;

            fee += secondTier * orderValue * 0.01;
        }

        // Minute 16 onwards = 2%
        if (delayMinutes > 15) {

            int thirdTier =
                    delayMinutes - 15;

            fee += thirdTier * orderValue * 0.02;
        }

        return fee;
    }

    public void processAccount(
            DeliveryAccount account,
            double amount,
            int delayMinutes) {

        double fee =
                account.calculateSurgeFee(delayMinutes);

        System.out.println(
                "Student: " + account.studentId +
                        " | Amount: " + amount +
                        " | Surge Fee: " + fee
        );
    }


    // Premium account
    static class Premium extends DeliveryAccount {

        public Premium(String studentId, double orderValue) {
            super(studentId, orderValue);
        }

        public Premium(String studentId) {
            super(studentId);
        }
    }


    public static void processBatch(
            DeliveryAccount[] accounts,
            double[] amounts,
            int[] delayMinutesArray) {

        int processed = 0;
        int nullSkipped = 0;
        int premiumCount = 0;
        int regularCount = 0;

        double totalSurgeFee = 0.0;

        // Only complete records are processed
        int n = Math.min(
                accounts.length,
                Math.min(
                        amounts.length,
                        delayMinutesArray.length
                )
        );

        for (int i = 0; i < n; i++) {

            // Handle null before instanceof
            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            try {

                double fee =
                        accounts[i].calculateSurgeFee(
                                delayMinutesArray[i]
                        );

                // Use instanceof to identify account type
                if (accounts[i] instanceof Premium) {

                    premiumCount++;

                    // Premium members pay 50% of normal surge fee
                    fee = fee * 0.50;

                } else {

                    regularCount++;
                }

                accounts[i].processAccount(
                        accounts[i],
                        amounts[i],
                        delayMinutesArray[i]
                );

                totalSurgeFee += fee;
                processed++;

            } catch (Exception e) {

                System.out.println(
                        "Invalid account skipped at index " + i
                );
            }
        }

        System.out.println();

        System.out.println(
                processed + " processed"
        );

        System.out.println(
                nullSkipped + " null skipped"
        );

        System.out.println(
                premiumCount + " premium"
        );

        System.out.println(
                regularCount + " regular"
        );

        System.out.println(
                "Grand total surge fees = Rs " +
                        totalSurgeFee
        );
    }
}