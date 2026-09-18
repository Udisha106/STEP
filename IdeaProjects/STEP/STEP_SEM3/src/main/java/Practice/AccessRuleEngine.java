package Practice;

public class AccessRuleEngine {

    static String classifyAccess(String fieldModifier, String accessorContext) {

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        if (fieldModifier.equals("private")) {
            if (accessorContext.equals("SAME_CLASS")) {
                return "ALLOWED";
            } else {
                return "DENIED";
            }
        }

        if (fieldModifier.equals("default")) {
            if (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE")) {
                return "ALLOWED";
            } else {
                return "DENIED";
            }
        }

        if (fieldModifier.equals("protected")) {

            if (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE") ||
                    accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {
                return "ALLOWED";
            } else {
                return "DENIED";
            }
        }

        return "DENIED";
    }

    static String summarizeBatch(String[][] attempts) {

        int allowed = 0;
        int denied = 0;

        for (int i = 0; i < attempts.length; i++) {

            String result = classifyAccess(attempts[i][0], attempts[i][1]);

            if (result.equals("ALLOWED")) {
                allowed++;
            } else {
                denied++;
            }
        }

        return "Allowed: " + allowed + " | Denied: " + denied;
    }

    static String describeContext(String accessorContext) {

        String[] words = accessorContext.split("_");
        String result = "";

        for (int i = 0; i < words.length; i++) {

            result = result + words[i].substring(0, 1).toUpperCase()
                    + words[i].substring(1).toLowerCase();

            if (i < words.length - 1) {
                result = result + " ";
            }
        }

        return result;
    }
}
