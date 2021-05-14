package fit.ome.leetcode;

/**
 * 最大子序和
 * https://leetcode-cn.com/problems/maximum-subarray/
 * 滑动窗口
 */
public class Code0053 {
    public int maxSubArray(int[] nums) {

        int[] preSum = new int[nums.length];
        preSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preSum[i] = nums[i] + preSum[i - 1];
        }
        int max = Integer.MIN_VALUE;
        int L = 0;
        int R = 0;
        while (L < nums.length) {
            if (L == R) {
                max = Math.max(max, nums[L]);

            } else {
                max = Math.max(max, preSum[R] - preSum[L] + nums[L]);
            }
            R++;
            if (R == nums.length) {
                L++;
                R = L;
            }
        }
        return max;
    }

    // 动态规划
    // 样本对照模型
    public int maxSubArray1(int[] nums) {
        int pre = 0;
        int maxAns = nums[0];
        for (int n : nums) {
            pre = Math.max(pre + n, n);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

    // 线段树
}
