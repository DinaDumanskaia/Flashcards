import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstLine = scanner.nextLine();
        String secondLine = scanner.nextLine();

        printBoolean(isAnagram(firstLine, secondLine));
    }

    private static void printBoolean(boolean anagram) {
        if (anagram) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    private static boolean isAnagram(String firstLine, String secondLine) {
        List<String> word1 = createList(firstLine);
        List<String> word2 = createList(secondLine);

        if (word1.size() != word2.size()) {
            return false;
        } else {
            for (String s : word1) {
                if (word2.contains(s)) {
                    word2.remove(s);
                }
            }
        }
        return word2.isEmpty();
    }

    private static List<String> createList(String line) {
        String[] word = line.split("");
        List<String> list = new ArrayList<>();

        for (String s : word) {
            list.add(s.toLowerCase());
        }
        return list;
    }
}
