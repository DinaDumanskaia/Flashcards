import java.util.*;

class SetUtils {

    public static Set<Integer> getSetFromString(String str) {
        String[] fromLine = str.split("\\s");
        Set<Integer> set = new TreeSet<>();
        for (String line : fromLine) {
            set.add(Integer.parseInt(line));
        }
        return set;
    }

    public static void removeAllNumbersGreaterThan10(Set<Integer> set) {
        Set<Integer> shortSet = new TreeSet<>();
        for (Integer i : set) {
            if (i <= 10) {
                shortSet.add(i);
            }
        }
        set.retainAll(shortSet);
    }

}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String numbers = scanner.nextLine();
        Set<Integer> set = SetUtils.getSetFromString(numbers);
        SetUtils.removeAllNumbersGreaterThan10(set);
        set.forEach(e -> System.out.print(e + " "));
    }
}