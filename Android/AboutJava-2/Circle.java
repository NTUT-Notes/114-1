class Circle {
    final float pi = 3.14159f;
    float r;
    // constructor does not need the return data type.
    public Circle() {
        r = 0;
    }

    public Circle(float r) {
        this.r = r;
    }
    @Override
    public float area() {
        return pi * r * r;
    }
    @Override
    public float perimeter() {
        return 2 * pi * r;
    }
    @Override
    public void show() {
        System.out.println("class Circle,");
        System.out.printf("radius: %.2f\n", r);
        System.out.printf("area: %.2f\nperimeter: %.2f\n", area(), perimeter());
    }
    public void check() {
        System.out.println("This is check() function!");
    }
}