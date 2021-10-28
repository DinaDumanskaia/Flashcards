import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstLine = scanner.nextLine();
        String secondLine = scanner.nextLine();

        if (firstLine.equals(secondLine)) {
            System.out.println(0);
        } else {
            System.out.println(findDifferences(firstLine, secondLine));
        }
    }

    private static int findDifferences(String firstLine, String secondLine) {
        List<String> word1 = createList(firstLine);
        List<String> word2 = createList(secondLine);

        int word2Size = word2.size();

        int counter = 0;

        for (String s : word1) {
            if (!word2.contains(s)) {
                counter++;
            } else {
                word2.remove(s);
            }
        }
        if (word2.size() + word1.size() == word2Size) {
            counter = word2.size();
        } else {
            for (String a : word2) {
                if (!word1.contains(a)) {
                    counter++;
                } else {
                    word1.remove(a);
                }
            }
        }
        return counter;
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