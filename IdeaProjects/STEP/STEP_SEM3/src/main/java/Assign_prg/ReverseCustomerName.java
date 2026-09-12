package Assign_prg;

public class ReverseCustomerName {

    static String reverseCustomerName(String customerName) {

        char[] characters = customerName.toCharArray();
        String reversed = "";

        for (int i = characters.length - 1; i >= 0; i--) {
            reversed += characters[i];
        }

        return reversed;
    }

    static void runReverseName() {

        String customerName = "Sunil";

        System.out.println("\n--- Reverse Customer Name ---");
        System.out.println("Original Name: " + customerName);
        System.out.println("Reversed Name: " +
                reverseCustomerName(customerName));
    }
}
