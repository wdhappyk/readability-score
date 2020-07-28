class Problem {

    public static void main(String[] args) {
        final String operator = args[0];
        final int a = Integer.parseInt(args[1]);
        final int b = Integer.parseInt(args[2]);

        switch (operator) {
            case "+":
                System.out.println(a + b);
                break;

            case "-":
                System.out.println(a - b);
                break;

            case "*":
                System.out.println(a * b);
                break;

            default:
                System.out.println("Unknown operator");
        }
    }
}