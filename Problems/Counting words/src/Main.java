import java.util.*;

class MapUtils {

    public static SortedMap<String, Integer> wordCount(String[] strings) {
        SortedMap<String, Integer> sortedMap = new TreeMap<>();
        int amount = 0;
        for (String lineForKey : strings) {
            for (String string : strings) {
                if (string.equals(lineForKey)) {
                    amount++;
                }
            }
            if (!sortedMap.entrySet().contains(lineForKey)) {
                sortedMap.put(lineForKey, amount);
            }
            amount = 0;
        }
        return sortedMap;
    }

    public static void printMap(Map<String, Integer> map) {
        map.forEach((k, v) -> System.out.println(k + " : " + v));
    }

}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = scanner.nextLine().split(" ");
        MapUtils.printMap(MapUtils.wordCount(words));
    }
}