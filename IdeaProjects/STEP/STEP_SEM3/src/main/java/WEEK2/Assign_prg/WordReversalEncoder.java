package WEEK2.Assign_prg;

public class WordReversalEncoder {

    static String reverseEachWord(String sentence) {

        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {

            StringBuilder reversed = new StringBuilder(words[i]);
            reversed.reverse();

            result.append(reversed);

            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    static void run() {

        String sentence = "hello club";

        System.out.println("\n--- Word Reversal Encoder ---");
        System.out.println(reverseEachWord(sentence));
    }
}
