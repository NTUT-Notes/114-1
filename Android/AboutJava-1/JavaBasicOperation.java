public class JavaBasicOperation {
    public static void main(String[] args) {
        StringAndNumber();

        //normalArithmeticOperation();
        //addition_1();
        //addition_2();
        //addition_3();

        //arithmetricOperation();
        //printfAndStringFormat();
    }

    static void StringAndNumber() {
        //==========================================
        // addition operations of Number and String:
        // Number + Number => Number
        // String + String => String (concatenate，串接)
        //
        // Number + String => String  (number => String)
        // String + Number => String

        int i = 123;
        int i2 = 101;
        String s = "456";
        String s2 = "abc";

        System.out.println("i + i2:");
        System.out.println(i + i2);
        System.out.println("s + s2:");
        System.out.println(s + s2);

        System.out.println("i + s:");
        System.out.println(i + s);
        System.out.println("s + i");
        System.out.println(s + i);
    }

    static void normalArithmeticOperation() {
        System.out.println("3 + 2 = " + 3 + 2);
        System.out.println("3 - 2 = " + (3 - 2));
        System.out.println("3 * 2 = " + 3 * 2);
        System.out.println("3 / 2 = " + 3 / 2);
        System.out.println("注意下列除法的運算規則及結果:");
        System.out.println("(float)3 / 2 = " + (float) 3 / 2);
        System.out.println("3 / (float)2 = " +  3 / (float)2);
        System.out.println("(float) 3 / 2 = " + (float) 3 / 2);
        System.out.println("(float) (3 / 2) = " + (float) (3 / 2));
        System.out.println("3. / 2 = " + 3. / 2);
        System.out.println("3 % 2 = " + 3 % 2);
    }

    static void addition_1() {
        int a = 5;
        int b = 3;
        System.out.println("a = " + a + "; b = " + b);
        System.out.println(a + b);
        System.out.println("a + b = " + a + b + " <--");
        System.out.println("a + b = " + (a + b));
    }

    static void addition_2() {
        String s1 = "Hello";
        String s2 = "World!";
        System.out.printf("s1=\"%s\",  s2=\"%s\"\n", s1, s2);
        System.out.println(String.format("s1=\"%s\",  s2=\"%s\"", s1, s2));
        System.out.println(s1 + s2);
        System.out.println("s1 + s2 = " + (s1 + s2));
        System.out.println("s1 + \" \" + s2 = " + (s1 + " " + s2));
    }

    static void addition_3() {
        String s = "123";
        System.out.printf("s=\"%s\"\n", s);
        System.out.println("s + 456 = " + s + 45 + 6);
        System.out.println("0 + s = " + 0 + s);
        System.out.println("s + (45 + 6) = " + s + (45 + 6));
    }

    // 使用類似 C printf() 的寫法:
    static void arithmetricOperation() {
        int a = 3;
        int b = 2;
        // using printf:
        System.out.printf("a = %d, b = %d\n\n", a, b);
        System.out.printf("%d + %d = %d\n", a, b, a+b);

        System.out.printf("%d - %d = %d\n", a, b, a-b);
        System.out.printf("%d * %d = %d\n", a, b, a*b);
        System.out.printf("%d / %d = %d\n", a, b, a/b);
        System.out.printf("%d %% %d = %d\n", a, b, a%b);
    }

    static void printfAndStringFormat() {
        int a = 3;
        int b = 2;

        // using printf and String.format:
        // printf: output to screen:
        System.out.printf("%d x %d = %d\n", a, b, a*b);

        // String.format: save to string variable:
        String result = String.format("%d x %d = %d\n", a, b, a*b);
        System.out.printf(result);

        System.out.printf("|" + result + "|");
    }
}
