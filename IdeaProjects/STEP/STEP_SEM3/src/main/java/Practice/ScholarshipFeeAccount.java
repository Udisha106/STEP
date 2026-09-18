package Practice;

class ScholarshipFeeAccount extends FeeAccount {

    private double scholarshipPercent;

    ScholarshipFeeAccount(String regNo, double totalFee,
                          double amountPaid, double scholarshipPercent) {

        super(regNo, totalFee, amountPaid);

        if (scholarshipPercent >= 0 &&
                scholarshipPercent <= 100) {

            this.scholarshipPercent = scholarshipPercent;
        }
    }

    double effectiveDue() {
        return getDue() * (1 - scholarshipPercent / 100);
    }
}
