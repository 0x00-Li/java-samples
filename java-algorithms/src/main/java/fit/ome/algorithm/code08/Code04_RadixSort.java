package fit.ome.algorithm.code08;

import java.util.Arrays;

/**
 * 基数排序
 * <p>
 * <p>
 * 对目标排序数组，进行按位拆分
 * <p>
 * 分别统计每个位置上的数的数量
 * <p>
 * 然后从个位到最高位，分别回填数据；每个位置都执行之后，完成排序
 * <p>
 * 整体复杂度为 O(k*n) ~=O(n) ,k为最大值的位数
 */
public class Code04_RadixSort {
    /**
     * 基数排序
     *
     * @param arr
     */
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1);
    }

    public static void radixSort(int[] arr, int l, int r) {
        // 基数0~9
        final int radix = 10;
        // 数组中的最大位数
        int maxBits = maxBits(arr);
        int[] help = new int[arr.length];
        // 从低位开始向高位遍历
        for (int i = 1; i <= maxBits; i++) {
/**
 * 技术词频统计表
 */
            int[] count = new int[10];
            for (int j = l; j <= r; j++) {
                int digit = getDigit(arr[j], i);
                count[digit]++;
            }

            // 修正count统计
            for (int j = 1; j < count.length; j++) {
                count[j] = count[j] + count[j - 1];
            }

            // 填充help数组
            for (int j = r; j>=l; j--) {
                int digit = getDigit(arr[j], i);
                help[count[digit] - 1] = arr[j];
                count[digit]--;
            }

            // 回填arr
            for (int j = 0; j < arr.length; j++) {
                arr[j] = help[j];
            }


        }


    }

    /**
     * 获取指定位置上的数字
     *
     * @param v 真实的数值
     * @param b 指定位置
     * @return
     */
    public static int getDigit(int v, int b) {
        return v / (int) Math.pow(10, b - 1) % 10;
    }

    public static int maxBits(int[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int maxBits = 0;
        while (max != 0) {
            maxBits++;
            max /= 10;
        }

        return maxBits;
    }

    // for test
    public static void sort(int[] arr) {
        Arrays.sort(arr);
    }

    public static int[] generateRandomArr(int maxVal, int maxSize) {
        int[] arr = new int[generateRandomMax(maxSize)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = generateRandomMax(maxVal);
        }
        return arr;
    }

    public static int generateRandomMax(int max) {
        return (int) (Math.random() * max) + 1;
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int maxVal = 1000;
        int maxSize = 100;
        int opTimes = 10000;
        for (int i = 0; i < opTimes; i++) {
            int[] arr = generateRandomArr(maxVal, maxSize);
            int[] arr1 = arr.clone();
            radixSort(arr1);
            int[] arr2 = arr.clone();
            sort(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("Oops!!!");
                break;
            }
        }
        System.out.println("finish");
    }
}

