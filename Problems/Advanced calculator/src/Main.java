import java.util.Arrays;

/* Please, do not rename it */
class Problem {

    public static void main(String[] args) {
        String operator = args[0];

        int[] numbers = new int[args.length - 1];

        for (int i = 1; i < args.length; ++i) {
            numbers[i - 1] = Integer.parseInt(args[i]);
        }

        switch (operator) {
            case "MAX":
                System.out.println(max(numbers));
                break;

            case "MIN":
                System.out.println(min(numbers));
                break;

            case "SUM":
                System.out.println(sum(numbers));
                break;

            default:
                System.out.println("Unknown operator");
        }
    }

    private static int max(int[] numbers) {
        int max = numbers[0];

        for (int n: numbers) {
            if (n > max) {
                max = n;
            }
        }

        return max;
    }

    private static int min(int[] numbers) {
        int min = numbers[0];

        for (int n : numbers) {
            if (n < min) {
                min = n;
            }
        }

        return min;
    }

    private static int sum(int[] numbers) {
        int sum = 0;

        for (int n : numbers) {
            sum += n;
        }

        return sum;
    }

}