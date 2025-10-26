import java.util.Random;
import java.util.Scanner;

public class SelectionSort {
    public static void main(String[] args) {
        int[] ary;
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);

        System.out.println("請輸入多少個元素?(>=10) ");
        String inp = scanner.next();    // re ad in the content in string
        int n = Integer.parseInt(inp);  // transfer string to int
        // or
        //int n = scanner.nextInt();

        if ( n < 10) n = 10;

        ary = new int[n];  // dynamically create an array with length of n
        for (int k = 0; k < ary.length; ++k)
            ary[k] = rand.nextInt(100);
        System.out.println("Before sorting:");
        showArray(ary);

        sort(ary);
        System.out.println("After sorting:");
        showArray(ary);
    }

    static void showArray(int[] ary) {
        String result = "";
        for (int k = 0; k < ary.length; ++k) {
            result += ary[k] + " ";
        }
        System.out.println(result);
    }

    static void sort(int[] ary) {  // in ascending order
        for (int p = 0; p < ary.length-1; p++) {
            for (int q = p+1; q < ary.length; q++) {
                if (ary[p] > ary[q]) {
                    int temp = ary[p];
                    ary[p] = ary[q];
                    ary[q] = temp;
                }
            }
        }
    }
}
