public class Part_3 {
    public static void main(String[] args) {
        for (int i=0; i<81; i++) {
            int a = i / 9;
            int b = i % 9;

            if (a < 8-b) {
                System.out.print(b == 0 ? "     " : "      ");
            } else {
                int product = (a+1)*(b+1);

                System.out.printf("%dx%d=", a+1, b+1);

                if (b != 0 && product < 10) {
                    System.out.print(" ");
                }

                System.out.print(product);
            }

            System.out.print(b == 8 ? "\n" : " ");            
        }
        return;
    }
}
