import java.util.Scanner;

public class Main {

    public static boolean isComposite(long number) {
        if (number == 1) {
            return false;
        }

        if (number % 2 == 0) {
            return number != 2;
        }

        int i = 3;

        while (number % i != 0) {
            i += 2;
        }

        return number != i;
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final long number = scanner.nextLong();
        System.out.println(isComposite(number));
    }
}