import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String number = scanner.nextLine();
        int intNumber = Integer.parseInt(number);
        String[] digits = number.split("");

        if (intNumber >= 0) {
            if (digits.length < 2) {
                System.out.println(0);
            } else {
                System.out.println(digits[digits.length - 2]);
            }
        }
    }
}