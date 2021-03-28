package fit.ome.algorithm.code05;

/**
 * 荷兰国旗问题
 * <p>
 * 给定一个数组arr，和一个整数num。请把小于num的数放在数组的左边，等于num的数放在中间，大于num的数放在数组的右边。
 * <p>
 * 要求额外空间复杂度O(1)，时间复杂度O(N)
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/28
 **/
public class Code02_PartitionAndQuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 6, 7, 4, 2, 1, 0, 8, 7, 5};
        printArr(arr);
        int[] arr1 = arr.clone();
        partitionAndQuick(arr1);
        printArr(arr1);
        int[] arr2 = arr.clone();
        quickSort2(arr2);
        printArr(arr2);

        int[] arr3 = arr.clone();
        quickSort3(arr3)
        ;
        printArr(arr3);
    }

    private static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 以右侧数为基准
    // [<=num|>num]
    public static void partitionAndQuick(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    // 以right位置的数为基准
    private static void process1(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = partition1(arr, left, right);
        process1(arr, left, mid - 1);
        process1(arr, mid + 1, right);

    }

    /**
     * 右侧数作为基准
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int partition1(int[] arr, int left, int right) {
        if (left > right) {
            return -1;
        }
        if (left == right) {
            return left;
        }
        int pl = left;
        int lessEqual = left - 1;// 小于支点值的位置，包含当前位置
        while (pl < right) {
            if (arr[pl] <= arr[right]) {
                swap(arr, pl, ++lessEqual);
            }
            pl++;
        }
        swap(arr, ++lessEqual, right);
        return lessEqual;
    }

    /**
     * l!=r
     *
     * @param arr
     * @param l
     * @param r
     */
    private static void swap(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        arr[l] = arr[r] ^ arr[l];
        arr[r] = arr[l] ^ arr[r];
        arr[l] = arr[l] ^ arr[r];
    }

    // [<num|==num|>num]
    private static void quickSort2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        process2(arr, 0, arr.length - 1);


    }

    private static void process2(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int[] flag = netherlandsFlag(arr, left, right);
        process2(arr, left, flag[0] - 1);
        process2(arr, flag[1] + 1, right);
    }

    /**
     * 标准荷兰国旗问题
     * < x ,==x ,>x
     *
     * @param arr
     * @param left
     * @param right
     * @return [start==x,end==x]
     */
    private static int[] netherlandsFlag(int[] arr, int left, int right) {
        if (left > right) {
            return new int[]{-1, -1};
        }
        if (left == right) {
            return new int[]{left, right};
        }
        int pl = left;
        int lessEqual = left - 1; // 左边界区 <x
        int moreEqual = right; // 右边界区 >x
        while (pl < moreEqual) {
            if (arr[pl] < arr[right]) {
                swap(arr, pl++, ++lessEqual);
            } else if (arr[pl] == arr[right]) {
                pl++;
            } else if (arr[pl] > arr[right]) {
                swap(arr, pl, --moreEqual);
            }
        }
        swap(arr, moreEqual, right);
        // 左右侧边界都是不包含相等的关系
        // 右侧是因为最右是参考值 ，所以不需要-1
        return new int[]{lessEqual + 1, moreEqual};
    }

    // 以上两种的复杂度，严格意义上来说
    // 1~O(n^2)
    // 2~O(n^2)
    // 第三种，增加随机性;随机快排
    // 3 ~ O(n*log n)
    private static void quickSort3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    private static void process3(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        swap(arr, left + (int) (Math.random() * (right - left + 1)), right);
        int[] flag = netherlandsFlag(arr, left, right);
        process3(arr, left, flag[0] - 1);
        process3(arr, flag[1] + 1, right);
    }


}
