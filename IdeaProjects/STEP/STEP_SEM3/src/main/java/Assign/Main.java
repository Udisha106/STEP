package Assign;

public class Main {

    public static void main(String[] args) {


        // ========================================
        // PROBLEM 1
        // ========================================

        System.out.println("===== PROBLEM 1 =====");

        System.out.println(
                AccessChecker.classifyAccess(
                        "private",
                        "SAME_CLASS"
                )
        );

        System.out.println(
                AccessChecker.classifyAccess(
                        "protected",
                        "DIFFERENT_PACKAGE"
                )
        );


        String[][] attempts = {

                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"protected", "SAME_PACKAGE"},
                {"protected", "SAME_CLASS"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
                AccessChecker.summarizeByModifier(attempts)
        );


        try {

            LibraryMember member =
                    new LibraryMember(
                            "LB9",
                            "BR1",
                            0,
                            "Priya Nair"
                    );

        } catch (Exception e) {

            System.out.println(
                    "LibraryMember construction rejected"
            );
        }


        // ========================================
        // PROBLEM 2
        // ========================================

        System.out.println("\n===== PROBLEM 2 =====");

        System.out.println(
                AccessChecker.classifyAccess(
                        "protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
                )
        );

        System.out.println(
                AccessChecker.classifyAccess(
                        "protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
                )
        );

        System.out.println(
                AccessChecker.describeContext(
                        "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
                )
        );


        // ========================================
        // PROBLEM 3
        // ========================================

        System.out.println("\n===== PROBLEM 3 =====");

        try {

            BookInventory b =
                    new BookInventory(3);

            b.checkOut();
            b.checkOut();
            b.checkOut();
            b.checkOut();

            System.out.println(
                    "Available after checkout: " +
                            b.getCopiesAvailable()
            );

            b.checkIn();
            b.checkIn();
            b.checkIn();
            b.checkIn();

            System.out.println(
                    "Available after check-in: " +
                            b.getCopiesAvailable()
            );

        } catch (Exception e) {

            System.out.println(
                    "Invalid inventory"
            );
        }


        // ========================================
        // PROBLEM 4
        // ========================================

        System.out.println("\n===== PROBLEM 4 =====");

        LibraryMemberBean m =
                new LibraryMemberBean();

        m.setMembershipId("LIB-8841");
        m.setMembershipId("FAKE-0000");

        System.out.println(
                "Membership ID: " +
                        m.getMembershipId()
        );


        LibraryMemberBean m2 =
                new LibraryMemberBean("Priya Nair");

        System.out.println(
                "Name: " +
                        m2.getName()
        );

        m2.setPremiumMember(true);

        System.out.println(
                "Premium: " +
                        m2.isPremiumMember()
        );

        m2.setSecurityAnswer("Blue");


        // ========================================
        // PROBLEM 5
        // ========================================

        System.out.println("\n===== PROBLEM 5 =====");

        try {

            LoanReceipt receipt =
                    new LoanReceipt(
                            "LIB-8841",
                            new String[]{
                                    "BK-100",
                                    "BK-101"
                            }
                    );

            String[] ids =
                    receipt.getBookIds();

            ids[0] = "HACKED";

            System.out.println(
                    "Original ID: " +
                            receipt.getBookIds()[0]
            );


            LoanReceipt corrected =
                    receipt.withCorrectedBookId(
                            0,
                            "BK-999"
                    );

            System.out.println(
                    "Corrected ID: " +
                            corrected.getBookIds()[0]
            );


            LoanReceipt[] receipts = {

                    new ReferenceOnlyLoanReceipt(
                            "LIB-001",
                            new String[]{"BK-200"},
                            "Reading Room 3"
                    ),

                    null,

                    new LoanReceipt(
                            "LIB-002",
                            new String[]{"BK-201"}
                    )
            };


            System.out.println(
                    LoanReceipt.processNightlyCirculation(
                            receipts
                    )
            );

        } catch (Exception e) {

            System.out.println(
                    "Receipt construction rejected"
            );
        }
    }
}
