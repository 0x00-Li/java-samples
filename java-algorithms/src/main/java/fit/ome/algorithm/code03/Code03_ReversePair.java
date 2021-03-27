package fit.ome.algorithm.code03;

/**
 * 求逆序对总数
 * 在一个数组中，任何一个前面的数啊，和任何一个后面的数b,如果（a,b）是逆序，就称为逆序对；
 * 返回数组中的所有的逆序对数量
 * <br>
 * 使用归并排序，在归并阶段，比较左右数进行求和
 * 1. 在每次归并的的时候，如果左侧的数大于右侧的数，就会大于右侧留存的所有的数
 * 2. 注意 左右相等的边界，相等的时候，应该让右侧数据，优先归并，保持左侧数据继续和右侧进行对比
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/27
 **/
public class Code03_ReversePair {
    public static int reversePair(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        return process(arr, left, mid) + process(arr, mid + 1, right) + merge(arr, left, mid, right);
    }

    private static int merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int pl = mid;
        // right end
        int er = mid + 1;
        int pr = right;
        int index = help.length - 1;
        int res = 0;
        while (pr >= er && pl >= left) {
            res += arr[pl] > arr[pr] ? (pr - er + 1) : 0;
            // 左右相等，让右侧优先归并
            help[index--] = arr[pl] > arr[pr] ? arr[pl--] : arr[pr--];
        }
        while (pr >= er) {
            help[index--] = arr[pr--];
        }
        while (pl >= left) {
            help[index--] = arr[pl--];
        }
        for (int i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }
        return res;
    }

    // for test
    public static int comparator(int arr[]) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > arr[i]) {
                    res++;
                }
            }
        }
        return res;
    }

    // for test
    public static int comparator2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private static int[] generatedRandomArr(int maxSize, int maxVal) {
        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxVal + 1)) - (int) (Math.random() * maxVal);
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
        int times = 10000;
        int maxVal = 100;
        int maxSize = 30;
        System.out.println("begin");
        for (int i = 0; i < times; i++) {
            int[] arr = generatedRandomArr(maxSize, maxVal);
            int comparator = comparator(arr.clone());
            int i2 = comparator2(arr.clone());
            int i1 = reversePair(arr.clone());
            if (comparator != i1 || i1 != i2) {
//            if (i1 != i2) {
//                printArr(arr);
                System.out.println(comparator);
                System.out.println(i1);
                System.out.println(i2);
                System.out.println("Oops!!!");
                break;
            }
        }
        System.out.println("finish");
    }
}
