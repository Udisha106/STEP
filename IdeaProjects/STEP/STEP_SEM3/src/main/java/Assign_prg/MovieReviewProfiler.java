package Assign_prg;

public class MovieReviewProfiler {

    static void classifyWordLengths(String review) {

        String[] words = review.split(" ");

        int shortWords = 0;
        int mediumWords = 0;
        int longWords = 0;

        for (String word : words) {

            int length = word.length();

            if (length >= 1 && length <= 4) {
                shortWords++;
            } else if (length <= 8) {
                mediumWords++;
            } else {
                longWords++;
            }
        }

        System.out.println("Short: " + shortWords);
        System.out.println("Medium: " + mediumWords);
        System.out.println("Long: " + longWords);
    }

    static void run() {

        String review =
                "This movie was absolutely fantastic and thrilling";

        System.out.println("\n--- Movie Review Word Length Profiler ---");
        classifyWordLengths(review);
    }
}
