import java.util.*;

class MapUtils {
    public static Map<Integer, String> getSubMap(TreeMap<Integer, String> map) {
        SortedMap<Integer, String> viewToMap;
        if (map.firstKey() % 2 != 0) {
            viewToMap = map.subMap(map.firstKey(), map.firstKey() + 4 + 1);
        } else {
            viewToMap = map.subMap(map.lastKey() - 4, map.lastKey() + 1);
        }
        return reverseMap(viewToMap);
    }

    private static Map<Integer, String> reverseMap(SortedMap<Integer, String> viewToMap) {
        Map<Integer, String> reversed = new TreeMap<>(Comparator.reverseOrder());
        for (Map.Entry<Integer, String> pair : new ArrayList<>(viewToMap.entrySet())) {
            reversed.put(pair.getKey(), pair.getValue());
        }

        return reversed;
    }
}

/* Do not modify code below */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TreeMap<Integer, String> map = new TreeMap<>();
        Arrays.stream(scanner.nextLine().split("\\s")).forEach(s -> {
            String[] pair = s.split(":");
            map.put(Integer.parseInt(pair[0]), pair[1]);
        });

        Map<Integer, String> res = MapUtils.getSubMap(map);
        res.forEach((k, v) -> System.out.println(k + " : " + v));
    }
}