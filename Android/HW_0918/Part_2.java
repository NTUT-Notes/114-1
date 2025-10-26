public class Part_2 {
    public static void main(String[] args) {
        String source = """
111111111
110000001
101000011
100100111
111111111
100100111
101000011
110000001
111111111
""";

        System.out.println("int[][] ary = {");
        String[] lines = source.split("\n");

        for (int i=0; i<lines.length; i++) {
            System.out.print("  ");

            String[] chars = lines[i].split("");
            printLine(chars);
            
            if (i != lines.length-1) {
                System.out.print(",");
            }
            
            System.out.println();
        }

        System.out.println("};");
    }

    static void printLine(String[] data) {
        System.out.print("{");

        for (int i=0; i<data.length; i++) {
            System.out.print(data[i]);

            if (i != data.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.print("}");

    }
}
