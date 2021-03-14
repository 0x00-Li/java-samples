package fit.ome.algorithm;

/**
 * 选择排序，目标是选出最小值放在合适的位置
 * <br>
 * 遍历数组，找到最小值的索引位置
 * 保存到目标位置
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/13
 **/
public class Code02_Selection {
    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 4, 56, 6};
        sort(arr);
        printArr(arr);
    }

    public static void sort(int[] arr) {
        // 临界值 arr.length==1 的时候，swap会清空数据
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            if (minIndex != i) {
                swap(arr, i, minIndex);
            }


        }
    }

    private static void printArr(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    private static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
