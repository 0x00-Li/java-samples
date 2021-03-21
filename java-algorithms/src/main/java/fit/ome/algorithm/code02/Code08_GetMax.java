package fit.ome.algorithm.code02;

/**
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/20
 **/
public class Code08_GetMax {
    public int getMax(int[] arr, int L, int R) {

        if (L == R) {
            return arr[L];
        } else {
            int m = L + ((R - L) >> 1);
            return Math.max(getMax(arr, L, m), getMax(arr, m + 1, R));
        }
    }
}
