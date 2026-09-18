package Assign;

public class Main {

    public static void main(String[] args) {

        // =====================================
        // PROBLEM 1
        // =====================================

        System.out.println("===== PROBLEM 1 =====");

        String[][] orders = {
                {"Ravi", "Paneer Butter Masala"},
                {"", "Chole Bhature"},
                {"Meera", " "},
                {"Divya", "Veg Biryani"}
        };

        FoodOrder.processBatch(orders);

        FoodOrder order =
                new FoodOrder(
                        "Ravi",
                        "Paneer Butter Masala"
                );

        order.markDelivered();
        order.markDelivered();


        // =====================================
        // PROBLEM 2
        // =====================================

        System.out.println("\n===== PROBLEM 2 =====");

        DeliverySlot slot1 =
                new DeliverySlot(
                        "ORD101",
                        "13:00-14:00"
                );

        DeliverySlot slot2 =
                new DeliverySlot("ORD102");

        System.out.println(
                "Slot 1 Peak: " +
                        slot1.isPeakHour()
        );

        System.out.println(
                "Slot 2 Peak: " +
                        slot2.isPeakHour()
        );


        // =====================================
        // PROBLEM 3
        // =====================================

        System.out.println("\n===== PROBLEM 3 =====");

        Canteen[] canteens = {

                new Canteen(
                        "HB3-C",
                        "Spice Junction",
                        3
                ),

                new Canteen(
                        "hb1-c",
                        "Grand Mess",
                        5
                ),

                new Canteen(
                        "HB2-C",
                        "Southern Treats"
                )
        };

        Canteen[] ranked =
                Canteen.rankCanteens(canteens);

        System.out.println("Ranked Canteens:");

        for (Canteen c : ranked) {
            System.out.println(
                    c.getCanteenCode()
            );
        }


        // =====================================
        // PROBLEM 4
        // =====================================

        System.out.println("\n===== PROBLEM 4 =====");

        SurgeFeeCalculator calculator =
                new SurgeFeeCalculator(1.0);

        System.out.println(
                "0 minutes: Rs " +
                        calculator.calculateSurgeFee(500, 0)
        );

        System.out.println(
                "1 minute: Rs " +
                        calculator.calculateSurgeFee(500, 1)
        );

        System.out.println(
                "16 minutes: Rs " +
                        calculator.calculateSurgeFee(500, 16)
        );


        // =====================================
        // PROBLEM 5
        // =====================================

        System.out.println("\n===== PROBLEM 5 =====");

        DeliveryAccount[] accounts = {

                new DeliveryAccount.Premium(
                        "STU001",
                        500
                ),

                null,

                new DeliveryAccount(
                        "STU002",
                        300
                )
        };

        double[] amounts = {
                500,
                400,
                300
        };

        int[] delayMinutesArray = {
                10,
                5,
                0
        };

        DeliveryAccount.processBatch(
                accounts,
                amounts,
                delayMinutesArray
        );
    }
}
