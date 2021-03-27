package fit.ome.algorithm.code03;

/**
 * 对于每个数num，求有多少个后面的数*2依然<num,求总个数
 * <br>
 * 使用归并排序，在归并过程中，进行逻辑处理：
 * 1.在进行归并的过程中，左右两侧都是有序的
 * 2.
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/27
 **/
public class Code04_BiggerThanRightTwice {
    public static int biggerThanRightTwice(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int left, int right) {
        // base case
        // 起始位置和终止位置相等，为单个数，直接停止递归
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        return process(arr, left, mid) + process(arr, mid + 1, right) + merge(arr, left, mid, right);
    }

    /**
     * 1.需要求左侧比右侧的两倍还大的时候，右侧的数的个数无法融合进入归并过程
     * 2. 因为需要进行两倍的关系对比，所有无法和归并过程进行融合，需要进行但对处理过程
     *
     * @param arr
     * @param left
     * @param mid
     * @param right
     * @return
     */
    private static int merge(int[] arr, int left, int mid, int right) {
        int ans = 0;
        int pl = mid;
        int pr = right;
        // 独立进行，满足条件的数的统计
        while (pl >= left && pr > mid) {
            if (arr[pl] > (arr[pr] << 1)) {
                ans += (pr - mid);
                pl--;
            } else {
                pr--;
            }
        }
// 左右部分进行归并
        int[] help = new int[right - left + 1];
        pl = mid;
        pr = right;
        int index = help.length - 1;
        while (pl >= left && pr > mid) {
            help[index--] = arr[pl] < arr[pr] ? arr[pr--] : arr[pl--];
        }
        while (pl >= left) {
            help[index--] = arr[pl--];
        }
        while (pr > mid) {
            help[index--] = arr[pr--];
        }

        for (int i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }

        return ans;
    }

    //for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (arr[j] << 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static int comparator2(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (arr[j] << 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // for test
    private static int[] generatedRandomArr(int maxSize, int maxVal) {
        int[] res = new int[(int) (Math.random() * (maxSize + 1))];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (Math.random() * (maxVal + 1)) - (int) (Math.random() * maxVal);
        }
        return res;
    }

    public static void main(String[] args) {
        int times = 100000;
        int maxSize = 100;
        int maxVal = 10000;
        System.out.println("begin");
        for (int i = 0; i < times; i++) {
            int[] arr = generatedRandomArr(maxSize, maxVal);
            int comparator = comparator(arr.clone());
            int i1 = comparator2(arr.clone());
            if (comparator != biggerThanRightTwice(arr.clone()) || i1 != comparator) {
                System.out.println("Oops!!!");
                break;
            }
        }
        System.out.println("finish");
    }
}
