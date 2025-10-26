public class JavaStaticStyle {
    public static void main(String[] args) {
        System.out.println("JavaStaticStyle");

        // scenario 1: call static methods in main directly
//        noStatic();  // NG!, only existing method can be called by static method
        withStatic();  // OK!

        // scenario 2: call non-static methods by existing object
        JavaStaticStyle jss = new JavaStaticStyle();
        jss.noStatic();    // OK!
        jss.withStatic();  // OK!
    }

    void noStatic() {
        System.out.println("noStatic():");
        System.out.println("function without static modifier");
    }

    static void withStatic() {
        System.out.println("withStatic():");
        System.out.println("function with static modifier");
    }
}
