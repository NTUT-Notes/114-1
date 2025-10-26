
public class JavaRandom {

    public static void main(String[] args) {
        JavaRandom rand = new JavaRandom();
        rand.aboutMathRandom();
    }

    public double random() {
        return Math.random();
    }

    public int randint(int n) {
        return (int) (n * Math.random());
    }

    public int randint(int a, int b) {
        if (a >= b) {
            System.out.println("Invalid arguments!");
            return -1;
        }
        return (int) ((b - a + 1) * Math.random());
    }

    public void aboutMathRandom() {
        for (int i = 0; i < 10; i++) {
            System.out.println(random());
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.println(randint(5));
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.println(randint(3, 8));
        }
        System.out.println();
    }
}
