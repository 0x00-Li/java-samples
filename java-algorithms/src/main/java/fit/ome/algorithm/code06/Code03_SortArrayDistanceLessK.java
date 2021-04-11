package fit.ome.algorithm.code06;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 已知一个几乎有序的数组。几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k，并且k相对于数组长度来说是比较小的。
 * <p>
 * 请选择一个合适的排序策略，对这个数组进行排序。
 * <p>
 * <p>
 * 解题思路：
 * <p>
 * 利用滑动窗口堆
 * <p>
 * 根据给出的K，建立K大小的堆，然后在堆内排序，不断扩展堆的索引为范围，直到完成排序
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/4/11
 **/
public class Code03_SortArrayDistanceLessK {
    public static void lessKSort(int[] arr, int k) {
        if (arr == null || arr.length < 2 || k == 0) {
            return;
        }
        // 解决步骤
        // 1. 前k个元素中，最小元素一定在其中
        // 2. 利用小根堆，获取每个组件最小，然后窗口向后滑动
        //
        //
        // 默认小根堆
        PriorityQueue<Integer> minHeap = new PriorityQueue();
        // k窗口内的索引指针
        int index = 0;
        // 这里讨论了数组长度小于k的时候的情况
        for (; index <= Math.min(arr.length - 1, k); index++) {
            minHeap.add(arr[index]);
        }
        // 全局放入拍好序的数据的指针
        int i = 0;
        for (; index < arr.length; i++, index++) {
            arr[i] = minHeap.poll();
            minHeap.add(arr[index]);
        }
        while (!minHeap.isEmpty()) {
            arr[i++] = minHeap.poll();
        }

    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    // for test
    private static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    private static int[] generateMoveLessKArr(int maxVal, int maxSize, int k) {
        int[] ans = new int[maxSize];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = maxRandom(maxVal) - (maxRandom(maxVal) - 1);
        }
        // 排序
        Arrays.sort(ans);
//        printArr(ans);
        // 在k个位置内随机移动
        boolean[] isSwap = new boolean[ans.length];
        for (int i = 0; i < ans.length; i++) {
            int j = Math.min(ans.length - 1,i+ maxRandom(k));

            if (!isSwap[i] && !isSwap[j]) {
                isSwap[i] = true;
                isSwap[j] = true;
                swap(ans, i, j);
            }
        }
//        printArr(ans);

        return ans;
    }

    private static int maxRandom(int mx) {
        return (int) (Math.random() * mx + 1);
    }

    private static boolean isEqual(int[] a1, int[] a2) {
        if (a1 == null && a1 == a2) {
            return true;
        }
        if (a1.length != a2.length) {
            return false;
        }
        for (int i = 0; i < a1.length; i++) {
            if (a1[i] != a2[i]) {
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
        int maxSize = 10;
        int opTimes = 10000;
        int k;
        for (int i = 0; i < opTimes; i++) {
            k = maxRandom(maxSize);
            int[] arr = generateMoveLessKArr(maxVal, maxSize, k);

            int[] a1 = arr.clone();
            lessKSort(a1, k);
            int[] a2 = arr.clone();
            comparator(a2);
            if (!isEqual(a1, a2)) {
                System.out.println("Oops!");
                System.out.println(k);
                printArr(arr);
                printArr(a1);
                printArr(a2);
                break;
            }

        }
        System.out.println("finish");
    }

}
