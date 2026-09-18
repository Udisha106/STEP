package Practice;

public class Main {

    public static void main(String[] args) {

        // =========================
        // PROBLEM 1
        // =========================

        System.out.println("PROBLEM 1");

        String[][] bookings = {
                {"Divya", "Chennai"},
                {"", "Bangalore"},
                {"Ravi123", "Pune"},
                {"Divya", "Chennai"},
                {" ", " "}
        };

        BusTicket.processBatch(bookings);


        // =========================
        // PROBLEM 2
        // =========================

        System.out.println("\nPROBLEM 2");

        FareSplitter fare =
                new FareSplitter("TRIP001", 100000, 3);

        double[] breakdown = fare.fareBreakdown();

        for (double amount : breakdown) {
            System.out.printf("%.2f ", amount);
        }

        System.out.println();


        // =========================
        // PROBLEM 3
        // =========================

        System.out.println("\nPROBLEM 3");

        BusRoute[] routes = {
                new BusRoute("RT205L", "Airport Express", 3),
                new BusRoute("rt201j", "City Central", 4),
                new BusRoute("RT299T", "Night Service")
        };

        BusRoute[] ranked =
                BusRoute.rankRoutes(routes);

        for (BusRoute route : ranked) {
            System.out.println(route.getRouteCode());
        }


        // =========================
        // PROBLEM 4
        // =========================

        System.out.println("\nPROBLEM 4");

        BoardingPenaltyCalculator calculator =
                new BoardingPenaltyCalculator(1.0);

        System.out.println(
                calculator.calculatePenalty(1000, 0)
        );

        System.out.println(
                calculator.calculatePenalty(1000, 1)
        );

        System.out.println(
                calculator.calculatePenalty(1000, 16)
        );


        // =========================
        // PROBLEM 5
        // =========================

        System.out.println("\nPROBLEM 5");

        BusTicketAccount[] accounts = {
                new Sleeper("BK001", 2000),
                null,
                new BusTicketAccount("BK002", 1200)
        };

        double[] amounts = {
                1200, 900, 700
        };

        int[] minutesLate = {
                10, 5, 0
        };

        processBatch(accounts, amounts, minutesLate);
    }


    public static void processBatch(
            BusTicketAccount[] accounts,
            double[] amounts,
            int[] minutesLate) {

        int processed = 0;
        int nullSkipped = 0;
        int sleeper = 0;
        int regular = 0;

        double totalPenalty = 0;

        int n = Math.min(
                accounts.length,
                Math.min(amounts.length, minutesLate.length)
        );

        for (int i = 0; i < n; i++) {

            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            if (accounts[i] instanceof Sleeper) {
                sleeper++;
            } else {
                regular++;
            }

            try {

                double penalty =
                        accounts[i].calculatePenalty(minutesLate[i]);

                accounts[i].processAccount(
                        accounts[i],
                        amounts[i],
                        minutesLate[i]
                );

                totalPenalty += penalty;
                processed++;

            } catch (Exception e) {
                System.out.println("Invalid account skipped");
            }
        }

        System.out.println();
        System.out.println(processed + " processed");
        System.out.println(nullSkipped + " null skipped");
        System.out.println(sleeper + " sleeper");
        System.out.println(regular + " regular");
        System.out.println(
                "Grand total penalties = ₹" + totalPenalty
        );
    }
}
