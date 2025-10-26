public class JavaClassBasic {

    public static void main(String[] args) {
        Base base = new Base();
        base.funC();
        System.out.println();

        Derived derived = new Derived();
        derived.funC();
        derived.funB();
        derived.funA();
    }

    static class Base {
        public void funA() {
            System.out.println("Function A!");
        }
        public void funB() {
            System.out.println("Function B!");
        }

        public void funC() {
            System.out.println("Function C in class Base.");
        }
    }

    static class Derived extends Base {
        // It is OK that with or without "override"
        // However, it will help us check the functions having the same in base class.

        @Override
        public void funC() {
            super.funC();
            System.out.println("這是函式 C in class Deriverd.");
        }
    }
}



