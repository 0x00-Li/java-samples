package fit.ome.little;

/**
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/16
 **/
public class Code02_Swap {
    /**
     * 空间复杂度 O(1)
     *
     *
     * <br>
     * i可以等于j
     *
     * @param a
     * @param i
     * @param j
     */
    public static void swap0(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    /**
     * @param a
     * @param i
     * @param j
     */
    public static void swap1(int[] a, int i, int j) {

        a[i] = a[i] + a[j];
        a[j] = a[i] - a[j];
        a[i] = a[i] - a[j];
    }

    /**
     * i 不能等于j
     *
     * @param a
     * @param i
     * @param j
     */
    public static void swap2(int[] a, int i, int j) {

        a[i] = a[i] ^ a[j];
        a[j] = a[i] ^ a[j];
        a[i] = a[i] ^ a[j];
    }
}
