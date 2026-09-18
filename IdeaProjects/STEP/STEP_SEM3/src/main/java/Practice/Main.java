package Practice;

public class Main {

    public static void main(String[] args) {

        // =====================================
        // PROBLEM 1
        // =====================================

        System.out.println("PROBLEM 1");

        try {

            EventTicket t1 =
                    new EventTicket("ST1", 500);

        } catch (IllegalArgumentException e) {

            System.out.println("Construction rejected");
        }

        WorkshopTicket w =
                new WorkshopTicket(
                        "STU2",
                        1200,
                        "AI/ML"
                );

        w.pay(500);

        System.out.println(
                w.getBalanceDue()
        );

        String[] ids = {
                "STU1",
                "ST1",
                "STU2",
                " ",
                "STU3"
        };

        System.out.println(
                EventTicket.registerBatch(ids, 500)
        );


        // =====================================
        // PROBLEM 2
        // =====================================

        System.out.println("\nPROBLEM 2");

        EventTicket standard =
                new EventTicket("STU1", 500);

        WorkshopTicket workshop =
                new WorkshopTicket(
                        "STU2",
                        1200,
                        "AI/ML"
                );

        PremiumWorkshopTicket premium =
                new PremiumWorkshopTicket(
                        "STU3",
                        2000,
                        "Cloud Native",
                        300
                );

        HackathonTicket hackathon =
                new HackathonTicket(
                        "STU4",
                        800,
                        "Byte Force"
                );

        standard.printTicket();
        System.out.println();

        workshop.printTicket();
        System.out.println();

        premium.printTicket();
        System.out.println();

        hackathon.printTicket();
        System.out.println();

        System.out.println(
                EventTicket.classifyGeneration(premium)
        );

        System.out.println(
                EventTicket.classifyGeneration(hackathon)
        );

        EventTicket[] allTickets = {
                standard,
                workshop,
                premium,
                hackathon
        };

        System.out.println(
                EventTicket.getTotalBalanceDue(allTickets)
        );


        // =====================================
        // PROBLEM 3
        // =====================================

        System.out.println("\nPROBLEM 3");

        WorkshopTicket lateWorkshop =
                new WorkshopTicket(1200);

        lateWorkshop.pay(1200);

        lateWorkshop.applyLateFee(100);

        System.out.println(
                lateWorkshop.getBalanceDue()
        );

        double[] history =
                lateWorkshop.getLateFeeHistory();

        for (int i = 0; i < history.length; i++) {
            System.out.print(history[i] + " ");
        }

        System.out.println();

        history[0] = 999;

        double[] historyAgain =
                lateWorkshop.getLateFeeHistory();

        for (int i = 0; i < historyAgain.length; i++) {
            System.out.print(historyAgain[i] + " ");
        }

        System.out.println();


        // =====================================
        // PROBLEM 4
        // =====================================

        System.out.println("\nPROBLEM 4");

        EventTicket[] printTickets = {
                new EventTicket(500),
                new WorkshopTicket(1200, "AI/ML")
        };

        System.out.println(
                EventTicket.batchPrint(printTickets)
        );


        // =====================================
        // PROBLEM 5
        // =====================================

        System.out.println("\nPROBLEM 5");

        EventTicket newTicket =
                new EventTicket(500);

        System.out.println(
                newTicket.ticketId
        );

        System.out.println(
                EventTicket.getTicketsIssued()
        );

        System.out.println(
                EventTicket.isValidPromoCode("F123A")
        );

        System.out.println(
                EventTicket.isValidPromoCode("F12A")
        );

        System.out.println(
                EventTicket.isValidPromoCode("X123A")
        );

        EventTicket paymentTicket =
                new EventTicket(500);

        paymentTicket.pay(200);

        paymentTicket.pay(200, "UPI");

        System.out.println(
                paymentTicket.getBalanceDue()
        );

        EventTicket[] settlement = {

                new GroupTicket(2000, 5),

                null,

                new EventTicket(500)
        };

        System.out.println(
                EventTicket.processNightlySettlement(
                        settlement
                )
        );
    }
}
