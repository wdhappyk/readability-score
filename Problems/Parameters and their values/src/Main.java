class Problem {

    public static void main(String[] args) {
        for (int i = 0; i < args.length; ++i) {
            System.out.print(args[i] + (i % 2 == 0 ? "=" : "\n"));
        }
    }
}