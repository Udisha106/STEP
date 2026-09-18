package Assign;

public class RaceEntry {

    private String bibNumber;
    protected double entryFee;
    private double amountPaid;

    private double[] lateFeeHistory;
    private int lateFeeCount;

    private static int bibCounter = 0;
    public final String entryCode;

    // Constructor for Problem 1
    public RaceEntry(String bibNumber, double entryFee) {

        String id = bibNumber.trim();

        if (id.length() < 4) {
            throw new IllegalArgumentException("Invalid bib number");
        }

        this.bibNumber = id;
        this.entryFee = entryFee;
        this.amountPaid = 0;

        lateFeeHistory = new double[10];
        lateFeeCount = 0;

        // Problem 5
        bibCounter++;
        entryCode = "BIB-" + (1000 + bibCounter);
    }

    // Constructor for Problem 5
    public RaceEntry(double entryFee) {

        this.bibNumber = null;
        this.entryFee = entryFee;
        this.amountPaid = 0;

        lateFeeHistory = new double[10];
        lateFeeCount = 0;

        bibCounter++;
        entryCode = "BIB-" + (1000 + bibCounter);
    }

    public void pay(double amount) {

        amountPaid = amountPaid + amount;
    }

    public void pay(double amount, String mode) {

        System.out.println("Paying via " + mode);

        pay(amount);
    }

    public double getBalanceDue() {

        return entryFee - amountPaid;
    }

    protected void applyLateFee(double amount) {

        entryFee = entryFee + amount;

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

    public String getBibNumber() {
        return bibNumber;
    }

    public void announce() {

        System.out.print(
                "Race Entry | Bib: " + bibNumber +
                        " | Balance: " + getBalanceDue()
        );
    }

    // Problem 1
    public static String registerBatch(
            String[] bibNumbers,
            double entryFee) {

        int registered = 0;
        int rejected = 0;

        for (int i = 0; i < bibNumbers.length; i++) {

            try {

                RaceEntry entry =
                        new RaceEntry(bibNumbers[i], entryFee);

                registered++;

            } catch (IllegalArgumentException e) {

                rejected++;
            }
        }

        return "Registered: " + registered +
                " | Rejected: " + rejected;
    }

    // Problem 2
    public static String classifyGeneration(
            RaceEntry entry) {

        if (entry instanceof EliteRunnerEntry) {

            return "Multilevel descendant (3 generations deep)";
        }

        if (entry instanceof RelayTeamEntry) {

            return "Hierarchical sibling (independent branch)";
        }

        if (entry instanceof RunnerEntry) {

            return "Direct subclass";
        }

        return "Base Race Entry";
    }

    // Problem 2
    public static double getTotalBalanceDue(
            RaceEntry[] entries) {

        double total = 0;

        for (int i = 0; i < entries.length; i++) {

            total = total + entries[i].getBalanceDue();
        }

        return total;
    }

    // Problem 4
    public static String announceAll(
            RaceEntry[] entries) {

        StringBuilder result =
                new StringBuilder();

        for (int i = 0; i < entries.length; i++) {

            /*
             * announce() is polymorphic.
             * Each object runs its own overridden method.
             */
            entries[i].announce();

            if (entries[i] instanceof RelayTeamEntry) {

                RelayTeamEntry relay =
                        (RelayTeamEntry) entries[i];

                result.append(
                        "[Team size via downcast: "
                                + relay.getTeamSize()
                                + "] "
                );
            }

            result.append("| ");
        }

        return result.toString();
    }

    // Problem 5
    public static boolean isValidDiscountCode(
            String code) {

        if (code == null || code.length() != 5) {
            return false;
        }

        if (code.charAt(0) != 'M') {
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

    // Problem 5
    public static int getBibCounter() {

        return bibCounter;
    }

    // Problem 5
    public static String settleNight(
            RaceEntry[] entries) {

        int processed = 0;
        int nullSkipped = 0;
        int relay = 0;
        int individual = 0;

        for (int i = 0; i < entries.length; i++) {

            if (entries[i] == null) {

                nullSkipped++;
                continue;
            }

            processed++;

            if (entries[i] instanceof RelayTeamEntry) {

                relay++;

            } else {

                individual++;
            }
        }

        return processed + " processed | " +
                nullSkipped + " null skipped | " +
                relay + " relay | " +
                individual + " individual";
    }
}
