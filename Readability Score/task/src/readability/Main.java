package readability;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Enter file name!");
            return;
        }

        String fileName = args[0];
        String text = null;
        try {
            text = readFileAsString(fileName);
        } catch (IOException e) {
            System.out.println("Can't read file: " + e.getMessage());
        }

        if (text == null) {
            System.out.println("No text");
            return;
        }

        final Scanner scanner = new Scanner(System.in);

        final String[] sentences = getSentences(text);
        final String[] words = getWords(text);

        final int wordsCount = words.length;
        final int sentencesCount = sentences.length;
        final int charactersCount = countCharacters(text);
        final int syllablesCount = countSyllables(words);
        final int polysyllablesCount = countPolysyllables(words);

        final double ariIndex = formatNumber(calcARI(charactersCount, wordsCount, sentencesCount));
        final int ariAge = getAge(ariIndex);
        final double fkIndex = formatNumber(calcFK(syllablesCount, wordsCount, sentencesCount));
        final int fkAge = getAge(fkIndex);
        final double smogIndex = formatNumber(calcSMOG(polysyllablesCount, sentencesCount));
        final int smogAge = getAge(smogIndex);
        final double clIndex = formatNumber(calcCL(charactersCount, wordsCount, sentencesCount));
        final int clAge = getAge(clIndex);

        final double avgAge = formatNumber((ariAge + fkAge + smogAge + clAge) / 4d);

        System.out.println("The text is:");
        System.out.println(text);

        System.out.println();
        System.out.println("Words: " + wordsCount);
        System.out.println("Sentences: " + sentencesCount);
        System.out.println("Characters: " + charactersCount);
        System.out.println("Syllables: " + syllablesCount);
        System.out.println("Polysyllables: " + polysyllablesCount);

        System.out.println();
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");

        final String input = scanner.next();
        boolean all = input.equalsIgnoreCase("all");

        System.out.println();

        if (all || input.equalsIgnoreCase("ARI")) {
            System.out.println("Automated Readability Index: " + ariIndex + " (about " + ariAge + " year olds).");
        }
        if (all || input.equalsIgnoreCase("FK")) {
            System.out.println("Flesch" + ((char) (8211)) + "Kincaid readability tests: " + fkIndex + " (about " + fkAge + " year olds).");
        }
        if (all || input.equalsIgnoreCase("SMOG")) {
            System.out.println("Simple Measure of Gobbledygook: " + smogIndex + " (about " + smogAge + " year olds).");
        }
        if (all || input.equalsIgnoreCase("CL")) {
            System.out.println("Coleman" + ((char) (8211)) + "Liau index: " + clIndex + " (about " + clAge + " year olds).");
        }

        System.out.println();
        System.out.println("This text should be understood in average by " + avgAge + " year olds.");
    }

    private static String[] getSentences(String text) {
        return text.split("[!?.]+");
    }

    private static int countSyllables(String word) {
        String lowerCaseWord = word.toLowerCase();
        Matcher match = Pattern.compile("([aioeuy]+)").matcher(lowerCaseWord);
        int count = 0;
        while (match.find()) {
            count++;
        }

        if (lowerCaseWord.endsWith("e")) {
            count--;
        }
        return Math.max(1, count);
    }

    private static int countSyllables(String[] words) {
        int count = 0;

        for (String word : words) {
            count += countSyllables(word);
        }

        return count;
    }

    private static int countPolysyllables(String[] words) {
        int count = 0;

        for (String word : words) {
            if (isPolysyllable(word)) {
                count++;
            }
        }

        return count;
    }

    private static boolean isPolysyllable(String word) {
        final int syllables = countSyllables(word);
        return syllables > 2;
    }

    private static String[] getWords(String text) {
        String[] fragments = text.split("\\s+");
        String[] words = new String[fragments.length];

        for (int i = 0; i < fragments.length; ++i) {
            words[i] = fragments[i].replaceAll("\\W", "");
        }

        return words;
    }

    private static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    private static int getAge(double score) {
        double value = Math.round(score);

        if (value == 1) {
            return 6;
        }
        if (value == 2) {
            return 7;
        }
        if (value == 3) {
            return 9;
        }
        if (value == 4) {
            return 10;
        }
        if (value == 5) {
            return 11;
        }
        if (value == 6) {
            return 12;
        }
        if (value == 7) {
            return 13;
        }
        if (value == 8) {
            return 14;
        }
        if (value == 9) {
            return 15;
        }
        if (value == 10) {
            return 16;
        }
        if (value == 11) {
            return 17;
        }
        if (value == 12) {
            return 18;
        }
        return 24;
    }

    private static int countCharacters(String text) {
        return text.replaceAll("\\s", "").length();
    }

    private static double calcARI(int chracters, int words, int sentences) {
        return Math.round((4.71 * chracters / words + 0.5 * words / sentences - 21.43) * 100) / 100d;
    }

    private static double calcFK(int syllables, int words, int sentences) {
        return 0.39 * words / sentences + 11.8 * syllables / words - 15.59;
    }

    private static double calcSMOG(int polysyllables, int sentences) {
        if (sentences == 0) {
            return 0;
        }
        return 1.043 * Math.sqrt(polysyllables * 30 / (double) sentences) + 3.1291;
    }

    private static double calcCL(int letters, int words, int sentences) {
        final double l = (double) letters / (double) words * 100;
        final double s = (double) sentences / (double) words * 100;
        return 0.0588 * l - 0.296 * s - 15.8;
    }

    private static double formatNumber(double n) {
        return Math.round(n * 100) / 100d;
    }
}
