package fit.ome.algorithm.code05;

/**
 * 给定一个数组arr，两个整数lower和upper，
 * <p>
 * 返回arr中有多少个子数组的累加和在[lower,upper]范围上
 * <br>
 * https://leetcode.com/problems/count-of-range-sum/
 * <br>
 * 1. 建立辅助数组 程度和给定数组长度相同，存储相关索引位置的前缀和；如：preSum[i] 保存的是sum(0,i) 位置的和的值
 * <p>
 * 问题转移：
 * 假设 0~i 整体累加和为x，题目求的范围是[l,up]
 * 求必须以i位置结尾的子数组有多少个在[l,up]范围上，
 * 等同于求，i之前的所有前缀和中有多少个前缀和在[x-up,x-l]上
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/28
 **/
public class Code01_CountRangeSum {
    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0 || lower > upper) {
            return 0;
        }
        long[] preSum = new long[nums.length];
        // 求出前缀和
        preSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preSum[i] = nums[i] + preSum[i - 1];
        }

        return process(preSum, 0, preSum.length - 1, lower, upper);
    }

    private static int process(long[] preSum, int left, int right, int lower, int upper) {
        // base case
        // 当左侧和右侧相等的时候，判断当前值是否在上下限之间
        if (left == right) {
            return preSum[left] >= lower && preSum[left] <= upper ? 1 : 0;
        }
        int mid = left + ((right - left) >> 1);
        // 优先进行目标数组进行求解

        return process(preSum, left, mid, lower, upper)
                + process(preSum, mid + 1, right, lower, upper)
                + merge(preSum, left, mid, right, lower, upper);
    }

    private static int merge(long[] arr, int left, int mid, int right, int lower, int upper) {
        int ans = 0;

// 在归并之前，优先求出以右组为参考，左组有多少个满足标准
        //
        //
        // 左右两侧都是有序的，不需要回退，所有，窗口的左右位置可以每次循环之后进行固定
        int winL = left;
        int winR = left;
        for (int i = mid + 1; i <= right; i++) {

            long min = arr[i] - upper;
            long max = arr[i] - lower;
            // 正常逻辑需要球在在左侧范围内，在[min,max]范围上的子数组个数；
            // 和在[,max]范围上减去在[,min)上的范围一样多，即是：count([min,max])=count([,max])-count([,min))
            //
            // 求出在<= max的范围有多少个子数组
            while (winR <= mid && arr[winR] <= max) {
                winR++;
            }
            // 求出在<min 的范围有多少个子数组
            while (winL <= mid && arr[winL] < min) {
                winL++;
            }
            ans += winR - winL;
        }
        // 进行正常逻辑的归并

        long[] help = new long[right - left + 1];
        int pl = left;
        int pr = mid + 1;
        int index = 0;
        while (pl <= mid && pr <= right) {
            help[index++] = arr[pl] < arr[pr] ? arr[pl++] : arr[pr++];
        }
        // 归并左侧剩余
        while (pl <= mid) {
            help[index++] = arr[pl++];
        }
        // 归并右侧剩余
        while (pr <= right) {
            help[index++] = arr[pr++];
        }
        // 数组复制
        for (int i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }


        return ans;
    }
}
