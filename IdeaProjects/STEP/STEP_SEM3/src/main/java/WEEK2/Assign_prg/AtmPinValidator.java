package WEEK2.Assign_prg;

public class AtmPinValidator {

    static void checkPinLength(String pin) {

        if (pin.length() != 4) {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    static void run() {

        String pin = "4820";

        System.out.println("\n--- ATM PIN Length Validator ---");
        checkPinLength(pin);
    }
}