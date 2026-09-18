package Assign;

 class LoanReceipt {

     final String memberId;
     final String[] bookIds;

    static int totalProcessed;

    static {
        totalProcessed = 0;
    }


    public LoanReceipt(
            String memberId,
            String[] bookIds) {

        if (bookIds == null) {
            throw new IllegalArgumentException(
                    "Book IDs cannot be null"
            );
        }

        String[] copy =
                new String[bookIds.length];

        for (int i = 0; i < bookIds.length; i++) {

            if (!bookIds[i].matches("BK-[0-9]{3}")) {
                throw new IllegalArgumentException(
                        "Invalid book ID"
                );
            }

            copy[i] = bookIds[i];
        }

        this.memberId = memberId;
        this.bookIds = copy;
    }


    public String[] getBookIds() {

        String[] copy =
                new String[bookIds.length];

        for (int i = 0; i < bookIds.length; i++) {
            copy[i] = bookIds[i];
        }

        return copy;
    }


    public LoanReceipt withCorrectedBookId(
            int index,
            String newId) {

        if (index < 0 ||
                index >= bookIds.length ||
                newId == null ||
                !newId.matches("BK-[0-9]{3}")) {

            throw new IllegalArgumentException(
                    "Invalid correction"
            );
        }

        String[] newBookIds =
                getBookIds();

        newBookIds[index] = newId;

        return new LoanReceipt(
                memberId,
                newBookIds
        );
    }


    static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (int i = 0; i < receipts.length; i++) {

            if (receipts[i] == null) {
                nullSkipped++;
                continue;
            }

            if (receipts[i]
                    instanceof ReferenceOnlyLoanReceipt) {

                referenceOnly++;

            } else {

                regular++;
            }

            processed++;
            totalProcessed++;
        }

        return processed +
                " processed | " +
                nullSkipped +
                " null skipped | " +
                referenceOnly +
                " reference-only | " +
                regular +
                " regular";
    }
}
// Reference-only receipt
class ReferenceOnlyLoanReceipt extends LoanReceipt {

    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(
            String memberId,
            String[] bookIds,
            String roomNumber) {

        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }
}


