import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sizeOfInputArray = Integer.parseInt(scanner.nextLine());
        String[] arrayLine = scanner.nextLine().split("\\s");
        int nNumber = Integer.parseInt(scanner.nextLine());

        List<Integer> list = makeAList(arrayLine);
        System.out.println(findNAmount(nNumber, list));
    }

    private static int findNAmount(int nNumber, List<Integer> list) {
        int counter = 0;
        for (int i : list) {
            if (i == nNumber) {
                counter++;
            }
        }
        return counter;
    }

    private static List<Integer> makeAList(String[] arrayLine) {
        List<Integer> list = new ArrayList<>();
        for (String s : arrayLine) {
            list.add(Integer.parseInt(s));
        }
        return list;
    }
}