package Assign;

class LibraryMember {

    private String membershipId;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    public LibraryMember(
            String membershipId,
            String branchCode,
            double finesOwed,
            String displayName) {

        String id = membershipId.trim();

        if (id.isEmpty() || id.length() < 4) {
            throw new IllegalArgumentException(
                    "Invalid membership ID"
            );
        }

        this.membershipId = id;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}
