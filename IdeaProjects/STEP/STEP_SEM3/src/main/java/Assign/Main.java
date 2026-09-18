package Assign;

public class Main {

    public static void main(String[] args) {

        // ==========================================
        // PROBLEM 1
        // ==========================================

        System.out.println("PROBLEM 1");

        try {

            RaceEntry r1 =
                    new RaceEntry("B1", 50);

        } catch (IllegalArgumentException e) {

            System.out.println("Construction rejected");
        }

        RunnerEntry runner =
                new RunnerEntry(
                        "BIB2001",
                        80,
                        "Open 10K"
                );

        runner.pay(30);

        System.out.println(
                runner.getBalanceDue()
        );

        String[] bibNumbers = {
                "BIB1",
                "B1",
                "BIB2"
        };

        System.out.println(
                RaceEntry.registerBatch(
                        bibNumbers,
                        80
                )
        );


        // ==========================================
        // PROBLEM 2
        // ==========================================

        System.out.println("\nPROBLEM 2");

        RunnerEntry runnerEntry =
                new RunnerEntry(
                        "BIB2001",
                        80,
                        "Open 10K"
                );

        EliteRunnerEntry eliteEntry =
                new EliteRunnerEntry(
                        "BIB3001",
                        150,
                        "Elite Full Marathon",
                        500
                );

        RelayTeamEntry relayEntry =
                new RelayTeamEntry(
                        "BIB4001",
                        300,
                        4
                );

        runnerEntry.announce();
        System.out.println();

        eliteEntry.announce();
        System.out.println();

        relayEntry.announce();
        System.out.println();

        System.out.println(
                RaceEntry.classifyGeneration(
                        eliteEntry
                )
        );

        System.out.println(
                RaceEntry.classifyGeneration(
                        relayEntry
                )
        );

        RaceEntry[] entries = {
                runnerEntry,
                eliteEntry,
                relayEntry
        };

        System.out.println(
                RaceEntry.getTotalBalanceDue(
                        entries
                )
        );


        // ==========================================
        // PROBLEM 3
        // ==========================================

        System.out.println("\nPROBLEM 3");

        RunnerEntry lateRunner =
                new RunnerEntry(80);

        lateRunner.pay(30);

        lateRunner.applyLateFee(20);

        System.out.println(
                lateRunner.getBalanceDue()
        );

        double[] history =
                lateRunner.getLateFeeHistory();

        for (int i = 0; i < history.length; i++) {

            System.out.print(
                    history[i] + " "
            );
        }

        System.out.println();

        // Try to tamper with copy
        history[0] = 999;

        double[] historyAgain =
                lateRunner.getLateFeeHistory();

        for (int i = 0;
             i < historyAgain.length;
             i++) {

            System.out.print(
                    historyAgain[i] + " "
            );
        }

        System.out.println();


        // ==========================================
        // PROBLEM 4
        // ==========================================

        System.out.println("\nPROBLEM 4");

        RaceEntry[] fleet = {
                runnerEntry,
                relayEntry
        };

        System.out.println(
                RaceEntry.announceAll(fleet)
        );


        // ==========================================
        // PROBLEM 5
        // ==========================================

        System.out.println("\nPROBLEM 5");

        System.out.println(
                RaceEntry.isValidDiscountCode(
                        "M123A"
                )
        );

        System.out.println(
                RaceEntry.isValidDiscountCode(
                        "M12A"
                )
        );

        System.out.println(
                RaceEntry.isValidDiscountCode(
                        "X123A"
                )
        );


        RaceEntry paymentEntry =
                new RaceEntry(50);

        paymentEntry.pay(10, "UPI");

        RaceEntry[] night = {

                eliteEntry,

                null,

                relayEntry
        };

        System.out.println(
                RaceEntry.settleNight(night)
        );

        System.out.println(
                "Bib Counter: " +
                        RaceEntry.getBibCounter()
        );
    }
}
