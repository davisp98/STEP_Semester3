public class VowelConsonantCounter {

    static void countVowelsAndConsonants(String text) {
        int vowels = 0, consonants = 0;
        String vowelSet = "aeiouAEIOU";

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == ' ') continue;
            if (Character.isLetter(c)) {
                if (vowelSet.indexOf(c) != -1) {
                    vowels++;
                } else {
                    consonants++;
                }
            }
        }
        System.out.println("Vowels: " + vowels + " | Consonants: " + consonants);
    }

    public static void main(String[] args) {
        countVowelsAndConsonants("Java Programming"); // Vowels: 5 | Consonants: 10
    }
}
