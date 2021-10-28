import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] line = scanner.nextLine().split("");

        System.out.println(line[0].equals("j") || line[0].equals("J"));
    }
}