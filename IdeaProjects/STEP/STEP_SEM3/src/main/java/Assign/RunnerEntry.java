package Assign;

public class RunnerEntry extends RaceEntry {

    private String category;

    public RunnerEntry(
            String bibNumber,
            double entryFee,
            String category) {

        super(bibNumber, entryFee);

        this.category = category;
    }

    // Constructor used in Problem 3
    public RunnerEntry(
            double entryFee) {

        super(entryFee);

        this.category = "";
    }

    public String getCategory() {

        return category;
    }

    // Problem 3
    @Override
    protected void applyLateFee(double amount) {

        super.applyLateFee(amount * 2);
    }

    @Override
    public void announce() {

        System.out.print(
                "Runner Entry | Bib: " +
                        getBibNumber() +
                        " | Category: " +
                        category +
                        " | Balance: " +
                        getBalanceDue()
        );
    }
}
