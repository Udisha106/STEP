package WEEK2.Practice_prg;

public class VowelConsonantCounter {

    static void countVowelsAndConsonants(String text) {

        int vowels = 0;
        int consonants = 0;

        for (int i = 0; i < text.length(); i++) {

            char ch = text.charAt(i);

            if (ch == 'a' || ch == 'e' || ch == 'i' ||
                    ch == 'o' || ch == 'u' ||
                    ch == 'A' || ch == 'E' || ch == 'I' ||
                    ch == 'O' || ch == 'U') {

                vowels++;
            } else if (ch != ' ') {
                consonants++;
            }
        }

        System.out.println("Vowels: " + vowels);
        System.out.println("Consonants: " + consonants);
    }

    static void run() {

        String text = "Java Programming";

        System.out.println("\n--- Vowel & Consonant Counter ---");
        countVowelsAndConsonants(text);
    }
}
