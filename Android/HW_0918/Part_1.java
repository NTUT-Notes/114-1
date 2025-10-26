public class Part_1 {

    public static void main(String[] args) {
        String source = """
int[][] ary = {
  {1, 1, 1, 1, 1, 1, 1, 1, 1},
  {1, 1, 0, 0, 0, 0, 0, 0, 1},
  {1, 0, 1, 0, 0, 0, 0, 1, 1},
  {1, 0, 0, 1, 0, 0, 1, 1, 1},
  {1, 1, 1, 1, 1, 1, 1, 1, 1},
  {1, 0, 0, 1, 0, 0, 1, 1, 1},
  {1, 0, 1, 0, 0, 0, 0, 1, 1},
  {1, 1, 0, 0, 0, 0, 0, 0, 1},
  {1, 1, 1, 1, 1, 1, 1, 1, 1}
};
""";
        source = source.replace("int[][] ary = {\n", "");
        source = source.replace("\n};\n", "");
        source = source.replace("{", "");
        source = source.replace("}", "");
        source = source.replace(",", "");
        source = source.replace(" ", "");

        System.out.println(source);
    }
}