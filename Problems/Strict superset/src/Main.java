import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static <T> boolean isStrictSuperset(Set<T> set1, Set<T> set2) {
        // write your code here
        if (!set1.equals(set2)) {
            Set<Boolean> bool = new HashSet<>();
            if (set2.size() >= set1.size()) {
                for (T t : set1) {
                    if (set2.contains(t)) {
                        bool.add(true);
                    } else {
                        bool.add(false);
                    }
                }
            } else {
                return false;
            }
            return !bool.contains(false);
        }
        return false;
    }

    /* Please do not change the code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Set<String> set1 = readStringSet(scanner);
        Set<String> set2 = readStringSet(scanner);

        System.out.println(isStrictSuperset(set1, set2));
    }

    private static Set<String> readStringSet(Scanner scanner) {
        return Arrays
                .stream(scanner.nextLine().split("\\s+"))
                .collect(Collectors.toSet());
    }
}