package fit.ome.algorithm.code06;

import java.util.Arrays;

/**
 * 堆排序
 *
 * <br>
 * 1. 通过heapInsert 逻辑将数组元素插入堆中，构建出大根堆，然后再有序弹出 O(N*logN)
 * <p>
 * 2. 通过heapify 方法，依次调整 最后构建出大根堆，然后有序弹出 O(logN)
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/4/11
 **/
public class Code02_HeapSort {

    /**
     * 堆排序开始
     *
     * @param arr
     */
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 通过heapInsert 构建大根堆
        // 复杂度为O(N*logN)
//        for (int i = 0; i < arr.length; i++) {
//            // 复杂度为O(logN)
//            heapInsert(arr, i);
//        }


        // 够过heapify 构建大根堆
        // 复杂度为O(N)
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }

        // 排序弹出
        // 大根堆，只能确定堆根的元素是最大的堆
        // 堆尾和最大交换，然后收缩堆大小，重新调整堆
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            // 必须保证heapSize>0,此逻辑控制了，边界
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }

    }

    /**
     * 插入数据的位置
     * <p>
     * 需要向上调整
     *
     * @param arr
     * @param index
     */
    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) >> 1;
        }
    }

    /**
     * 指定位置元素被变更，向下调整
     *
     * @param arr
     * @param index
     */
    private static void heapify(int[] arr, int index, int heapSize) {
        int left = (index << 1) + 1;
        while (left < heapSize ) {
            // 重定向 maxIndex值的位置
            //
            // 讨论 左孩子和父
            int maxIndex = arr[left] > arr[index] ? left : index;
            // 讨论 右孩子和上一步的最大索引位置的值
            maxIndex = (left + 1) < heapSize && arr[left + 1] > arr[maxIndex] ? left + 1 : maxIndex;
            if (maxIndex == index) {
                // 当前位置比两个孩子都大，中止调整
                break;
            }
            swap(arr, index, maxIndex);
            index = maxIndex;
            left = (index << 1) + 1;
        }
    }


    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
// ---------------------------------------- 堆排序结束

    // for test
    public static void comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        Arrays.sort(arr);

    }

    // for test
    public static int[] generateRandomArray(int maxVal, int maxSize) {
        int[] ans = new int[maxRandom(maxSize)];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = maxRandom(maxVal) - (maxRandom(maxVal) - 1);
        }
        return ans;
    }

    private static int maxRandom(int max) {
        return (int) ((Math.random() * max) + 1);
    }

    private static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null && arr1 == arr2) {
            return true;
        }
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

    private static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxVal = 1000;
        int maxSize = 100;
        int odTimes = 100000;
        System.out.println("start....")
        ;
        for (int i = 0; i < odTimes; i++) {
            int[] arr = generateRandomArray(maxVal, maxSize);
            int[] a1 = arr.clone();
            heapSort(a1);
            int[] a2 = arr.clone();
            comparator(a2);
            if (!isEqual(a1, a2)) {

                System.out.println("Oops!!!!");
                printArr(arr);
                printArr(a1);
                printArr(a2);
                break;
            }
        }
        System.out.println("finish....");
    }

}
