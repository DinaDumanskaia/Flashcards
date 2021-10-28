import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class ListUtils {

    /**
     * It splits the passed list into a sequence of sublists with a predefined size 
     */
    public static <T> List<List<T>> splitListIntoSubLists(List<T> list, int subListSize) {
        List<List<T>> sublists = new ArrayList<>();
        List<T> smallList;

        // write your code here
        if (subListSize >= list.size()) {
            sublists.add(list);
        } else {
            for (int i = 0; i < list.size(); ) {
                smallList = new ArrayList<>();
                if (list.size() - i >= subListSize) {
                    for (int u = i; u < i + subListSize; u++) {
                        smallList.add(list.get(u));
                    }
                    i += subListSize;
                } else {
                    for (int u = i; u < list.size(); u++) {
                        smallList.add(list.get(u));
                    }
                    i = list.size();
                }
                sublists.add(smallList);
            }
        }
        return sublists;
    }
}

/* Please, do not modify code in this class */
public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final String[] values = scanner.nextLine().split("\\s+");

        final List<Integer> list = Arrays.asList(values).stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        final int subListSize = Integer.parseInt(scanner.nextLine());

        final List<List<Integer>> subLists = ListUtils.splitListIntoSubLists(list, subListSize);

        subLists.forEach(subList -> {
            final String representation = subList.stream().map(Object::toString).collect(Collectors.joining(" "));
            System.out.println(representation);
        });
    }
}