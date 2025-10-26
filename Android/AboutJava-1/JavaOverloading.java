public class JavaOverloading {

    public static void main(String[] args) {
        // 透過物件引用 class JavaOverloading 中的成員函式:
        JavaOverloading jo = new JavaOverloading();

        jo.square(3);
        jo.square(1.2f);
        jo.square(5.5);
    }

    // function overloading: (但不支援參數的初值設定！)
    // 函式名稱相同，但其參數的 個數 與 型態 不完全相同！
    // 參數都相同，回傳值不同，並不符合 overloading。
    // 因為一開始就要能分辨(透過參數)，而不是要等到最後才來區分(回傳值)。
    void square(int n) {
        System.out.printf("the square of %d is %d.\n", n, n*n);
    }

    void square(float n) {
        System.out.printf("the square of %f is %f.\n", n, n*n);
    }

    void square(double n) {
        System.out.printf("the square of %f is %f.\n", n, n*n);
    }
}
