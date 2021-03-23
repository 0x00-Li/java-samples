package fit.ome.algorithm.code03;

/**
 * 归并排序
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/22
 **/
public class Code01_MergeSort {
    /**
     * 递归实现
     *
     * @param arr
     */
    public static void mergeSort(int[] arr) {
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int l, int r) {
        if (l == r) {// base case
            return;
        }
        int mid = l + ((r - l) >> 1);
        process(arr, l, mid);
        process(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    public static void merge(int[] arr, int l, int mid, int r) {

        int[] help = new int[r - l + 1];
        int i = 0;
        int pl = l;
        int pr = mid + 1;
        while (pl <= mid && pr <= r) {
            help[i++] = arr[pl] <= arr[pr] ? arr[pl++] : arr[pr++];
        }
        while (pl <= mid) {
            help[i++] = arr[pl++];
        }
        while (pr <= r) {
            help[i++] = arr[pr++];
        }
        for (int j = 0; i < help.length; j++) {
            arr[l + j] = help[j];
        }
    }
    // 迭代实现
}
