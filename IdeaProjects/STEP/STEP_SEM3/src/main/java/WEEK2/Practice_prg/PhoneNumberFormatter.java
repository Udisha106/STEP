package WEEK2.Practice_prg;

public class PhoneNumberFormatter {

    static String maskPhoneNumber(String phone) {

        if (phone.length() != 10) {
            return "Invalid phone number";
        }

        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return "Invalid phone number";
            }
        }

        StringBuilder result = new StringBuilder("XXXXXX");

        result.insert(6, "-");

        result.append(phone.substring(6));

        return result.toString();
    }

    static void run() {

        String phone = "9876543210";

        System.out.println("\n--- Masked Phone Number Formatter ---");
        System.out.println(maskPhoneNumber(phone));
    }
}
