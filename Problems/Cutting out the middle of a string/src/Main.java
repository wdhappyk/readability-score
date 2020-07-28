import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String word = scanner.next();
        final StringBuilder sb = new StringBuilder(word);
        final int len = sb.length();
        final int center = len / 2 - (len % 2 == 0 ? 1 : 0);
        final int offset = len % 2 == 0 ? 2 : 1;
        sb.delete(center, center + offset);
        System.out.println(sb.toString());
    }
}