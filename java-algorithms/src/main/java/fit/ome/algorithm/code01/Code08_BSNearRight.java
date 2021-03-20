package fit.ome.algorithm.code01;

import java.util.Arrays;

/**
 * 在一个有序数组中，找<=某个数最右侧的位置
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/20
 **/
public class Code08_BSNearRight {
    public static int nearestRight(int[] arr, int v) {
        int l = 0;
        int r = arr.length - 1;
        int index = -1;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (arr[m] <= v) {
                index = m;
                // 现在 < 区间的话，需要，左侧前进
                l = m + 1;
            } else {
                // > 区间，r，后退
                r = m - 1;
            }
        }
        return index;
    }

    public static int test(int[] arr, int v) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= v) {
                return i;
            }
        }
        return -1;
    }

    public static int[] generatedRandomArray(int maxSize, int maxValue) {

        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue));
        }
        return arr;
    }

    public static void main(String[] args) {
        int times = 10000;
        int maxSize = 20;
        int maxValue = 100;
        System.out.println("begin");
        for (int i = 0; i < times; i++) {
            int v = (int) (Math.random() * (maxValue + 1));
            int[] arr = generatedRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int test = test(arr, v);
            int nearestRight = nearestRight(arr, v);
            if (test != nearestRight) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish");
    }
}
