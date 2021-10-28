import java.util.*;

public class Main {

    static void changeList(List<String> list) {
        String line = "";
        for (String word : list) {
            if (word.length() >= line.length()) {
                line = word;
            }
        }

        for (int i = 0; i < list.size(); i++) {
            list.set(i, line);
        }
        // write your code here
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        List<String> lst = Arrays.asList(s.split(" "));
        changeList(lst);
        lst.forEach(e -> System.out.print(e + " "));
    }
}