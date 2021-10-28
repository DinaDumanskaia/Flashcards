import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] line = scanner.nextLine().split("\\s");
        int counter = 0;
        if (line.length == 3) {
            for (String s : line) {
                if (Integer.parseInt(s) > 0) {
                    counter++;
                }
            }
        }

        if (counter == 1) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}