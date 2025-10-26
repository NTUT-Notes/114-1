import java.util.Scanner;
import java.util.StringTokenizer;

public class StringOperation {
    // python like long string in Java:
    // 可以使用多列的字串表示法:
    String longText = """
    This is line 1
      This is second line
    第三列文字!      
    """;   // 以結束(或底下)的 """ 來定位！ => 底定！

    String rule = """
    // The rules of String addition:
    // String + String => String
    // String + number => String + String(number) => String
    // number + String => String(number) + String => String
    """;

    public void stringAddition() {
        System.out.println(rule);  // show the rule of addition between String and number.
        System.out.printf("longText =\n%s\n", longText);

        System.out.println("stringAddition()");
        String msg = "123";
        System.out.println(0 + msg);
        System.out.println(msg + 456);
        System.out.println(0 + msg + 789);
        System.out.println(msg + msg);
        System.out.println("End of stringAddition()!");

    }

    public void stringGeneration() {
        System.out.println("\nstringGeneration()");
        int a = 3, b = 4;
        System.out.printf("a = %d, b = %d\n", a, b);

        System.out.println("\na + \" + \"  + b + \" = \" + a+b");
        System.out.println(a + " + "  + b + " = " + a+b);

        System.out.println("\na + \" + \"  + b + \" = \" + (a+b)");
        System.out.println(a + " + "  + b + " = " + (a+b));

        System.out.println("\nSystem.out.printf(\"%d + %d = %d\\n\", a, b, a+b);");
        System.out.printf("%d + %d = %d\n", a, b, a+b);

        String result = String.format("%d + %d = %d", a, b, a+b);
        System.out.println("\nString result = String.format(\"%d + %d = %d\", a, b, a+b);");
        System.out.printf("result = |%s|\n", result);
        System.out.println("End of stringGeneration!");
    }

    public void stringToken() {
        String msg = " How   old are   you!  ";
        StringTokenizer st = new StringTokenizer(msg);
        System.out.println("StringTokenizer st = new StringTokenizer(msg);");
        while(st.hasMoreTokens()) {
            System.out.println("|" + st.nextToken() + "|");
        }
        System.out.println("End of stringToken!");
    }

    public void stringEquals() {
        String msg = " How   old are   you!  ";
        StringTokenizer st = new StringTokenizer(msg);
        System.out.println("StringTokenizer st = new StringTokenizer(msg);");
        while(st.hasMoreTokens()) {
            System.out.println("|" + st.nextToken() + "|");
        }
        System.out.println("End of stringEquals!");
    }

    public void stringScanner() {
        String msg = " How   old are   you!  ";
        Scanner st = new Scanner(msg);
        System.out.println("Scanner st = new Scanner(msg);");
        while(st.hasNext()) {
            System.out.println("|" + st.next() + "|");
        }
        System.out.println("End of stringScanner!");
    }

    public void stringSplit() {
        String msg = " How   old are   you!  ";
        msg = msg.strip();
        String[] items = msg.split("\s+");

        // or
//        String[] items = msg.strip().split("\s+");
        System.out.println("String[] items = msg.strip().split(\"\\s+\");");
        for (String it : items) {
            System.out.println("|" + it + "|");
        }
        System.out.println("End of stringSplit!");
    }

    public void subStringDemo() {
        String msg = "abcdefghijk";
        System.out.println(msg.repeat(2));

        System.out.println(msg.substring(4));
        System.out.println(msg.substring(2, 5));  // 5-2=3 chars
    }

    public void anotherStringOperation() {
        // printf() and String.format():
        System.out.printf("(%d, %d)", 3, 4);
        String position = String.format("(%d, %d)", 3, 4);
        System.out.println(position);

        // replace and replaceAll:
        String msg = "{1, 2, 3}";
        System.out.println(msg);
        msg = msg.replace("{", "|");
        System.out.println(msg);
        msg = msg.replaceAll("[{|,}]", " ");
        System.out.println(msg);

        // compare the two strings:
        String s1 = "123";
        String s2 = "123";
        System.out.printf("s1==s2 => %s\n", s1==s2); // Primitive Types
        System.out.printf("s1.equals(s2) => %s  <-- better!\n", s1.equals(s2));

        // try toi show following output:
        // 1,2,3,4,5
    }
}
