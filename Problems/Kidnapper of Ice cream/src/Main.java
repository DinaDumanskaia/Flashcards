import java.util.LinkedList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] availableWords = scanner.nextLine().split("\\s");
        String[] note = scanner.nextLine().split("\\s");

        LinkedList<String> newspaper = createList(availableWords);
        System.out.println(writeLetter(newspaper, note));
    }

    private static String writeLetter(LinkedList<String> newspaper, String[] note) {
        int counter = 0;
        for (String word : note) {
            if (newspaperContain(newspaper, word)) {
                counter++;
                newspaper.remove(word);
            }
        }
        if (counter == note.length) {
            return "You get money";
        } else {
            return "You are busted";
        }
    }

    private static boolean newspaperContain(LinkedList<String> newspaper, String word) {
        for (String s : newspaper) {
            if (s.equals(word)) {
                return true;
            }
        }
        return false;
    }

    private static LinkedList<String> createList(String[] availableWords) {
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < availableWords.length; i++) {
            list.add(i, availableWords[i]);
        }
        return list;
    }
}