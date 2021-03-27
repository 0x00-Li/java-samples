package fit.ome.algorithm.code03;

/**
 * 思路：
 * 整体思路：通过归并排序，进行左右分区；在左右merge之前，对左侧小于右侧的数进行累计（乘积）数的求和
 * <br>
 * 1. 归并排序
 * 2. 在合并阶段，判断左侧比右侧小的时候，右侧的位置，然后求出右侧有多少个数，比当前左侧的数大
 * 3. 可以得出在当前的左侧数，在右侧有多少个数比当前的数大
 * 4. 说明在求和过程中的，当前左侧的数，需要进行多少次累加
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/27
 **/
public class Code02_SmallSum {

    public static int smalSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        return process(arr, left, mid) +
                +process(arr, mid + 1, right) +
                +merge(arr, left, mid, right);

    }

    // 在合并的过程中优先求和
    public static int merge(int[] arr, int l, int m, int r) {

        int[] help = new int[r - l + 1];
        int left = l;
        int right = r;
        int rStart = m + 1;
        int index = 0;
        int res = 0;
        while (left <= m && rStart <= right) {

            res += arr[left] < arr[rStart] ? arr[left] * (right - rStart + 1) : 0;
            help[index++] = arr[left] < arr[rStart] ? arr[left++] : arr[rStart++];
        }
        while (left <= m) {
            help[index++] = arr[left++];
        }
        while (rStart <= right) {
            help[index++] = arr[rStart++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return res;
    }

    // 对数器对比方法，暴力求解
    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
    }

    public static int[] generatedRandomArr(int maxVal, int maxSize) {
        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxVal + 1)) - (int) (Math.random() * (maxVal));
        }
        return arr;
    }

    private static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int times = 1000;
        int maxVal = 100;
        int maxSize = 20;
        System.out.println("begin");
        for (int i = 0; i < times; i++) {
            int[] arr = generatedRandomArr(maxVal, maxSize);

            int comparator = comparator(arr.clone());
            int i1 = smalSum(arr.clone());
            if (comparator != i1) {
                printArr(arr);
                System.out.println(comparator + "==" + i1);
                System.out.println("Oops!!!");
                return;
            }

        }
        System.out.println("finish");
    }
}
