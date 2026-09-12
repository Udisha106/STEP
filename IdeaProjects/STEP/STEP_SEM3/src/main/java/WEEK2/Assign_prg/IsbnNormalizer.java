package WEEK2.Assign_prg;

public class IsbnNormalizer {

    static String normalizeCode(String raw) {

        raw = raw.trim();

        if (raw.length() < 3) {
            return raw;
        }

        String publisherCode = raw.substring(0, 3).toUpperCase();
        String remaining = raw.substring(3);

        return publisherCode + remaining;
    }

    static String validateAndFormat(String code) {

        if (code.length() != 13) {
            return "Invalid: wrong length";
        }

        for (int i = 0; i < 3; i++) {

            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        for (int i = 3; i < code.length(); i++) {

            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: body must contain only digits";
            }
        }

        String publisherCode = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7);

        StringBuilder result = new StringBuilder();

        result.append("[");
        result.append(publisherCode);
        result.append("] YEAR: ");
        result.append(year);
        result.append(" | CATALOG: ");
        result.append(catalog);

        return result.toString();
    }

    static void run() {

        String raw = " pen2026004251 ";

        System.out.println("\n--- Library ISBN Normalizer & Validator ---");

        String normalized = normalizeCode(raw);

        System.out.println(validateAndFormat(normalized));
    }
}