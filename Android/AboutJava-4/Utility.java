public class Utility {
    public static void markline() {
        System.out.println();
        for (int k = 0; k < 25; ++k)
            System.out.print("-");
        System.out.println();
    }

    public static void markline(int len) {
        System.out.println();
        for (int k = 0; k < len; ++k)
            System.out.print("-");
        System.out.println();
    }

    public static void markline(int len, String mark) {
        System.out.println();
        for (int k = 0; k < len; ++k)
            System.out.print(mark);
        System.out.println();
    }
}
