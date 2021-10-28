import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        printNextEvenNumber(number);
    }

    private static void printNextEvenNumber(int number) {
        if (number % 2 != 0) {
            System.out.println(number + 1);
        } else {
            System.out.println(number + 2);
        }
    }
}