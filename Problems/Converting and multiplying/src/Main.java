import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        while (true) {
            String line = scanner.nextLine();

            if (line.equals("0")) {
                break;
            }

            try {
                System.out.println(Integer.parseInt(line) * 10);
            } catch (Exception e) {
                System.out.println("Invalid user input: " + line);
            }
        }
    }
}