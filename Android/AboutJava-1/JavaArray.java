public class JavaArray {

    public static void main(String[] args) {
        JavaArray ja = new JavaArray();
        ja.testArray1();
        ja.test2DArray_1();
        ja.test2DArray_2();
        ja.test2DArray_3();

    }

    public void testArray1() {
        /*
        物件變數 與 物件 的關係，就好比
        公車駕駛 與 公車。
        公車駕駛拿到公車的鑰匙後，就可以去開那輛公車！
        只要有該輛公車鑰匙的駕駛，都能夠取開那輛公車。
         */
        //*/
        // 陣列也是物件！
        // 宣告陣列變數，並指定陣列資料: 先招考進駕駛，並指定要開的公車。
        int[] ary = new int[] {1, 2, 3, 4};
//        int[] ary = {1, 2, 3, 4};  // 簡寫形式！

        // 宣告陣列變數，並指定初值:
        int[] ary2;  // 先招考進駕駛。

//        ary2 = {1, 3, 5, 7};  // NG!
        // 再指向陣列資料: (稍後配給公車，且要是一輛公車)
        ary2 = new int[] {1, 3, 5, 7};  // OK!

        for (int i = 0; i < ary2.length; i++) {
            System.out.println(ary2[i]);
        }

        //
        int[] ary3 = new int[100]; // 包含100個元素的int陣列。
        int index = 0;
        for (int a : ary3) {
            System.out.print(a + " ");  // 觀察初始值！
            index++;
            if ( index % 20 == 0)
                System.out.println();
        }
    }

    public void test2DArray_1() {
        // Java Multi-Dimensional Arrays:
        // https://www.w3schools.com/java/java_arrays_multi.asp
        int[][] ary = {
            {1, 2, 3},
            {4, 5, 6}
        };
        System.out.println(ary.length);
        for (int r = 0; r < 2; ++r) {
            for (int c = 0; c < 3; c++) {
                System.out.printf("ary[%d][%d]= %d ", r, c, ary[r][c]);
            }
            System.out.println();
        }
    }
    public void test2DArray_2() {
        // Java Multi-Dimensional Arrays:
        // https://www.w3schools.com/java/java_arrays_multi.asp
        int[][] ary;
        ary = new int[][] {
                {1, 2, 3},
                {4, 5, 6}
        };

        System.out.println(ary.length);
        for (int r = 0; r < ary.length; ++r) {
            for (int c = 0; c < ary[r].length; c++) {
                System.out.printf("ary[%d][%d]= %d ", r, c, ary[r][c]);
            }
            System.out.println();
        }
    }

    public void test2DArray_3() {
        // Java Multi-Dimensional Arrays:
        // https://www.w3schools.com/java/java_arrays_multi.asp
        int[][] ary;
        ary = new int[10][];
        int index = 0;
        for (int r = 0; r < ary.length; ++r) {
            ary[r] = new int[r+1];
            for (int c = 0; c < ary[r].length; ++c) {
                ary[r][c] = ++index;
            }
        }

        System.out.println(ary.length);
        for (int r = 0; r < ary.length; ++r) {
            for (int c = 0; c < ary[r].length; c++) {
                System.out.printf("ary(%d,%d)=%2d ", r, c, ary[r][c]);
            }
            System.out.println();
        }
    }
}
