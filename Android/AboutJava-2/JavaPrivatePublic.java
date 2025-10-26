public class JavaPrivatePublic {

    public static void main(String[] args) {
        /*
        modifiers, public and private, are just declared the access rule from outside the class.
        It won't affect that anything defined in the class definition.
         */
        pri_method();
        pub_method();

        //
        JavaPrivatePublic jpp = new JavaPrivatePublic();
        System.out.println("From object of class JavaPrivatePublic,");
        jpp.pri_method();
        jpp.pub_method();
    }

    private static void pri_method() {
        System.out.println("private method: pri_method().");
    }

    public static void pub_method() {
        System.out.println("public method: pub_method().");
    }
}
