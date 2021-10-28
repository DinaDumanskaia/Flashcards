import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = Integer.parseInt(scanner.nextLine());

        Set<String> knownWords = fillTheSet(scanner, length);
        int lines = Integer.parseInt(scanner.nextLine());
        Set<String> linesToCheck = fillTheSet(scanner, lines);

        Set<String> unknownWords = spellChecker(knownWords, linesToCheck);
        printSet(unknownWords);
    }

    private static void printSet(Set<String> unknownWords) {
        for (String a : unknownWords) {
            System.out.println(a);
        }
    }

    private static Set<String> spellChecker(Set<String> knownWords, Set<String> linesToCheck) {
        Set<String> set = new HashSet<>();

        for (String line : linesToCheck) {
            String[] fromLine = line.split("\\s");
            for (String s : fromLine) {
                if (!knownWords.contains(s.toLowerCase())) {
                    set.add(s);
                }
            }
        }
        return set;
    }

    private static Set<String> fillTheSet(Scanner scanner, int length) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < length; i++) {
            set.add(scanner.nextLine().toLowerCase());
        }
        return set;
    }
}