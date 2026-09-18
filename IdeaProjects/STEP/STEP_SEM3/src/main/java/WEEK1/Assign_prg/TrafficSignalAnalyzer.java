package WEEK1.Assign_prg;

public class TrafficSignalAnalyzer {

    static void findLongestStreak(String signalLog) {

        int currentStreak = 1;
        int longestStreak = 1;

        char longestColor = signalLog.charAt(0);

        for (int i = 1; i < signalLog.length(); i++) {

            if (signalLog.charAt(i) == signalLog.charAt(i - 1)) {
                currentStreak++;
            } else {
                currentStreak = 1;
            }

            if (currentStreak > longestStreak) {
                longestStreak = currentStreak;
                longestColor = signalLog.charAt(i);
            }
        }

        System.out.println("Longest Streak: '" + longestColor +
                "' repeated " + longestStreak + " times");
    }

    static void run() {
        String signalLog = "RRGGGYRR";

        System.out.println("\n--- Traffic Signal Streak Analyzer ---");
        findLongestStreak(signalLog);
    }
}
