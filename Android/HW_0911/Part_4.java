public class Part_4 {
    public static void main(String[] args) {
        int[][] inhibit = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 1, 1},
            {1, 0, 0, 1, 0, 0, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 1, 0, 0, 1, 1, 1},
            {1, 0, 1, 0, 0, 0, 0, 1, 1},
            {1, 1, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
        };
        for (int i=0; i<81; i++) {
            int a = i / 9;
            int b = i % 9;

            if (inhibit[a][b] != 1) {
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
    }
}
