package Assign;

class BookIssue {

    String title;
    String borrowerName;
    int daysOverdue;

    // Constructor
    BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }

    // Calculates fine
    double fineAmount() {
        if (daysOverdue > 0) {
            return daysOverdue * 5;
        } else {
            return 0;
        }
    }

    // Checks whether the book is overdue by more than 14 days
    boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    // Static because this method calculates the total for
    // many BookIssue objects, while fineAmount() belongs
    // to one particular BookIssue object.
    static double totalFineCollected(BookIssue[] issues) {

        double total = 0;

        for (BookIssue issue : issues) {
            total += issue.fineAmount();
        }

        return total;
    }
}
