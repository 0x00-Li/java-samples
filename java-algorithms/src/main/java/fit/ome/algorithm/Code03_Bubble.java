package fit.ome.algorithm;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/16
 **/
public class Code03_Bubble {

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    arr[j] = arr[j] ^ arr[j + 1];
                    arr[j + 1] = arr[j] ^ arr[j + 1];
                    arr[j] = arr[j] ^ arr[j + 1];
                }
            }
        }
    }

    public static void comparetor(int[] arr) {
        Arrays.sort(arr);
    }

    public static int[] generatedRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * maxValue);
        }
        return arr;
    }

    public static int[] copyArr(int[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i];
        }
        return result;
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
        int times = 10000;
        int maxSize = 100;
        int maxValue = 500;
        for (int i = 0; i < times; i++) {
            int[] arr = generatedRandomArray(maxSize, maxValue);
            int[] arr1 = copyArr(arr);
            comparetor(arr);
            sort(arr1);
            if (!isEqual(arr, arr1)) {
                System.out.println("oops!");
            }
        }
        System.out.println("finish");
    }
}
