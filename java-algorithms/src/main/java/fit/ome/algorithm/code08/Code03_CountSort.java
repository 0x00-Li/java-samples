package fit.ome.algorithm.code08;

import java.util.Arrays;

/**
 * 不基于比较的排序
 * <p>
 * 基于计数的排序
 * <p>
 * 桶排序
 *
 * 限制：
 * 必须知道数组中的最大值
 *
 *
 */
public class Code03_CountSort {

    /**
     * arr的数据规模不超过 200
     *
     * @param arr
     */
    public static void countSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
//        求出给定数组中的 最大值
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        // 针对数组中的每个数值建桶
        int[] bucket = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            // 包含有值的桶的值增加
            bucket[arr[i]]++;
        }
        // 从桶中获取数据，就是已经排好的顺序
        int index = 0;
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i]-- > 0) {
                arr[index++] = i;
            }
        }
    }

    // for test
    public static void sortArr(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArr(int maxVal, int maxSize) {
        int[] ans = new int[generateMax(maxSize)];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateMax(maxVal);
        }
        return ans;
    }

    public static int generateMax(int max) {
        return (int) (Math.random() * max) + 1;
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int maxSize = 30;
        int maxVal = 50;
        int opTimes = 10000;
        for (int i = 0; i < opTimes; i++) {
            int[] arr = generateRandomArr(maxVal, maxSize);
            int[] arr1 = arr.clone();
            countSort(arr1);
            int[] arr2 = arr.clone();
            sortArr(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("Oops!!!");
            }
        }
        System.out.println("finish");
    }

}
