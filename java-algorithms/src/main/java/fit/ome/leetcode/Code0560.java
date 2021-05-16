package fit.ome.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 和为K的子数组
 * <p>
 * https://leetcode-cn.com/problems/subarray-sum-equals-k/
 * <p>
 * <p>
 * 滑动窗口
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/5/16
 **/
public class Code0560 {
    /**
     * 滑动窗口 复杂度O(n)
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int ans = 0;
        // 前缀和数组加速
        int[] preSum = new int[nums.length];
        preSum[0] = nums[0];
        ans += preSum[0] == k ? 1 : 0;
        for (int i = 1; i < nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
            if (preSum[i] == k) {
                ans++;
            }
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (preSum[j] - preSum[i - 1] == k) {
                    ans++;
                }
            }
        }
        return ans;
    }

    /**
     * 前缀和优化
     * <p>
     * 先统计前缀和，前缀和的相同值，可能会重复，存入hash
     * m=<preSum,count>
     * v1-v2==k 时有子数组数为m(v1)*m(v2)
     * 可以在计算过程中直接统计
     * preSum1 到来时，count+=m(k-preSum1)
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum1(int[] nums, int k) {
        int ans = 0;
        // 前缀和数组加速
        int preSum = 0;
        Map<Integer, Integer> preSumMap = new HashMap<>();
        // 填充0值,用于处理边界值刚好为k时的求解问题
        preSumMap.put(0, 1);
        for (Integer num : nums) {
            preSum += num;
            ans += preSumMap.getOrDefault(preSum - k, 0);
            preSumMap.put(preSum, preSumMap.getOrDefault(preSum, 0) + 1);
        }
        return ans;
    }
}
