import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final List<String> lines = Arrays
                .stream(scanner.nextLine().split("\\s+"))
                .collect(Collectors.toList());

        System.out.println(lines);
    }
}