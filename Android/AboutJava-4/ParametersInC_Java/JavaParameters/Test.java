public class Test {
    public static void main(String[] args) {
        System.out.println("Hello world form Java!\n");

        if (args.length < 1) {
            System.out.println("No parameter(s) assigned.");
            System.exit(0);
        }

        for (int i = 0; i < args.length; ++i) {
            System.out.printf("%d: %s\n", i, args[i]);
        }
    }
}