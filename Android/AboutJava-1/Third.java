public class Third {

    public static void main(String[] args){
        System.out.println("The Third Java!");

        System.out.println("hello" + " " + "world");

        System.out.println("after staticFunction();");
        staticFunction();
        System.out.println("after Third.staticFunction();");
        Third.staticFunction();

        // 先生成 class Third 的物件，再經由該物件來呼叫其成 member functions:
        Third third = new Third();  // <-- 個別住戶或成員！
        third.showTriangle(5);
        System.out.println();
        third.showTriangle(9, '$');

        System.out.println("after third.staticFunction();");
        third.staticFunction();  // 將 static 函式當成公共設施！
    }

    // static function: <-- 當成公共設施！
    static void staticFunction() {
        System.out.println("This is staticFunction!");
    }

    // function overloading: (但不支援參數的初值設定！)
    // 函式名稱相同，但其參數的 個數 與 型態 不完全相同！
    void showTriangle(int n) {
        String msg = "";
        for (int i = 0; i < n; i++) {
            msg += "*";
            System.out.println(msg);
        }
    }

    void showTriangle(int n, char mark) {
        String msg = "";
        for (int i = 0; i < n; i++) {
            msg += mark;
            System.out.println(msg);
        }
    }
}
