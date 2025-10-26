import java.util.Random;

public class RandomNumber {
    Random rand = new Random();

    public void showRandomNumber() {
        System.out.println("rand.nextInt():");
        for (int k = 0; k <= 5; ++k) {
            System.out.println(rand.nextInt());
        }
        System.out.println();

        System.out.println("rand.nextInt(): second times");
        for (int k = 0; k <= 5; ++k) {
            System.out.println(rand.nextInt());
        }
        System.out.println();

        System.out.println("rand.nextInt(3):");
        for (int k = 0; k <= 5; ++k) {
            System.out.println(rand.nextInt(3));
        }
        System.out.println();

        // rand.setSeed(seed_value):
        rand.setSeed(123);
        System.out.println("first, run the rand.setSeed(123);");
        for (int k = 0; k <= 5; ++k) {
            System.out.println(rand.nextInt());
        }
        System.out.println("");

        rand.setSeed(123);
        System.out.println("second, run the rand.setSeed(123);");
        for (int k = 0; k <= 5; ++k) {
            System.out.println(rand.nextInt());
        }
        System.out.println("");

        System.out.println();
    }
}
