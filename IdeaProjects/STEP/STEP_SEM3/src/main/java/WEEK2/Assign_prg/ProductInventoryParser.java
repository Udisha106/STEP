package WEEK2.Assign_prg;

public class ProductInventoryParser {

    static void parseInventoryRecord(String csvLine) {

        String[] fields = csvLine.split(",");

        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        System.out.println("Product: " + fields[0] +
                " | SKU: " + fields[1] +
                " | Qty: " + fields[2]);
    }

    static void run() {

        String csvLine = "Wireless Mouse,WM-2201,150";

        System.out.println("\n--- Product Inventory CSV Parser ---");
        parseInventoryRecord(csvLine);
    }
}
