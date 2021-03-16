package fit.ome.algorithm;

/**
 * 插入排序
 * <br>
 * 1. 选择出一个元素向前比较，如果比前面的元素小，进行交换
 * 2. 持续 1 步骤 ，找到比本身大的元素停止进行，元素交换
 * 3. 如果，一直比较到第0个元素都比自身大，和0号元素交换
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/13
 **/
public class Code01_InsertionSort {

    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 4, 56, 6};
        sort(arr);
        printArr(arr);
    }

    private static void printArr(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void sort(int[] arr) {
        // 进行N次遍历
        for (int i = 0; i < arr.length; i++) {
            // 随着N的推进
            // 1
            // 2
            // n-3
            // n-2
            // n-1
            // 等差数列
            // 等差数列求和公式：
            // 整体复杂度：O(n2)


            for (int j = i + 1; j > 0 && j < arr.length; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                }

            }
        }

    }

    private static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
