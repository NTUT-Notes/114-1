public class Part_5 {
    public static void main(String[] args) {
        int[][] jaggedArray = new int[10][];

        int index = 1;
        for (int r=0; r<jaggedArray.length; r++) {
            jaggedArray[r] = new int [r+1];
            for (int c=0; c<jaggedArray[r].length; c++) {
                jaggedArray[r][c] = index++;
            }
        }

        for (int r=0; r<jaggedArray.length; r++) {
            for (int c=0; c<jaggedArray[r].length; c++) {
                System.out.printf("ary(%d,%d)=", r, c);

                if (jaggedArray[r][c] < 10) {
                    System.out.print(" ");
                }

                System.out.printf("%d ", jaggedArray[r][c]);
            }

            System.out.println();
        }
    }
}
