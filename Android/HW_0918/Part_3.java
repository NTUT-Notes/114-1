public class Part_3 {
    public static void main(String[] args) {
        Triangle pattern1 = new Triangle(1, 1, 100);
        System.out.println("=== Pattern 1 ===");
        pattern1.check();
        System.out.println();

        System.out.println("=== Pattern 2 ===");
        Triangle pattern2 = new Triangle(3, 4, 5);
        pattern2.show();
    }
}

class Triangle {
    final float pi = 3.14159f;
    float a, b, c;
    // constructor does not need the return data type.
    public Triangle() {
        a = 0;
        b = 0;
        c = 0;
    }

    public Triangle(float a, float b, float c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public float area() {
        float s = (this.a + this.b + this.c) / 2;
        return (float) Math.sqrt(s * (s-a) * (s-b) * (s-c));
    }

    public float perimeter() {
        return this.a + this.b + this.c;
    }

    public void show() {
        System.out.println("class Triangle,");
        System.out.printf("  Side A: %.2f\n", this.a);
        System.out.printf("  Side B: %.2f\n", this.b);
        System.out.printf("  Side C: %.2f\n", this.c);
        System.out.printf("area: %.2f\nperimeter: %.2f\n", area(), perimeter());
    }

    public void check() {
        float sum = this.a + this.b + this.c;
        
        if (sum-this.a <= this.a || 
            sum-this.b <= this.b ||
            sum-this.c <= this.c 
        ) {
            System.out.println("This is not a valid triangle !");
        } else {
            System.out.println("This is a valid triangle !");
        }
    }
}