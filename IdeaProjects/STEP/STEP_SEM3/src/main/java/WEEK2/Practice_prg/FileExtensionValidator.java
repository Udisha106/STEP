package WEEK2.Practice_prg;

public class FileExtensionValidator {

    static String validateFileExtension(String filename) {

        int dotPosition = filename.lastIndexOf('.');

        if (dotPosition == -1) {
            return "Rejected — invalid file type";
        }

        String extension = filename.substring(dotPosition + 1);

        if (extension.equalsIgnoreCase("pdf") ||
                extension.equalsIgnoreCase("docx") ||
                extension.equalsIgnoreCase("zip")) {

            return "Accepted";
        }

        return "Rejected — invalid file type";
    }

    static void run() {

        String filename = "Assignment1.PDF";

        System.out.println("\n--- File Extension Validator ---");
        System.out.println(validateFileExtension(filename));
    }
}
