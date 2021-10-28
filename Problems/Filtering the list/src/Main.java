import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final String[] values = scanner.nextLine().split("\\s+");

        final List<Integer> list = Arrays.asList(values).stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> newList = filterList(list);
        printReverse(newList);
    }

    private static void printReverse(List<Integer> newList) {
        for (int i = newList.size() - 1; i >= 0; i--) {
            System.out.print(newList.get(i));
            System.out.print(" ");
        }
    }

    private static List<Integer> filterList(List<Integer> list) {
        List<Integer> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 != 0) {
                newList.add(list.get(i));
            }
        }
        return newList;
    }
}