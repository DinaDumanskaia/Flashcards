import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] borders = scanner.nextLine().split("\\s");
        int lengthOfMap = Integer.parseInt(scanner.nextLine());
        TreeMap<Integer, String> map = new TreeMap<>();

        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split("\\s");
            map.put(Integer.parseInt(line[0]), line[1]);
        }

        int lowBorder;
        int highBorder;
        if (Integer.parseInt(borders[0]) > Integer.parseInt(borders[1])) {
            lowBorder = Integer.parseInt(borders[1]);
            highBorder = Integer.parseInt(borders[0]);
        } else {
            lowBorder = Integer.parseInt(borders[0]);
            highBorder = Integer.parseInt(borders[1]);
        }

        SortedMap<Integer, String> newMap = map.subMap(lowBorder, highBorder + 1);

        printMap(newMap);
    }

    private static void printMap(SortedMap<Integer, String> newMap) {
        for (Map.Entry<Integer, String> entry : newMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
